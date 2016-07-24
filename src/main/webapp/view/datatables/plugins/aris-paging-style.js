(function ($) {
	function calcDisableClasses(oSettings) {
		var start = oSettings._iDisplayStart;
		var length = oSettings._iDisplayLength;
		var visibleRecords = oSettings.fnRecordsDisplay();
		var all = length === -1;

		var page = all ? 0 : Math.ceil(start / length);
		var pages = all ? 1 : Math.ceil(visibleRecords / length);

		var disableFirstPrevClass = (page > 0 ? '' : oSettings.oClasses.sPageButtonDisabled);
		var disableNextLastClass = (page < pages - 1 ? '' : oSettings.oClasses.sPageButtonDisabled);

		return {
			'previous': disableFirstPrevClass ? disableFirstPrevClass : enableClassName,
			'next': disableNextLastClass ? disableFirstPrevClass : enableClassName,
			'previous btn': disableFirstPrevClass ? disableFirstPrevClass : enableClassName,
			'next btn': disableNextLastClass ? disableNextLastClass : enableClassName
		};
	}

	function calcCurrentPage(oSettings) {
		return Math.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength) + 1;
	}

	function calcPages(oSettings) {
		return Math.ceil(oSettings.fnRecordsDisplay() / oSettings._iDisplayLength);
	}

	var previousClassName = 'previous btn';
	var nextClassName = 'next btn';
	var enableClassName = "btn-primary";

	var paginateClassName = 'paginate';
	var paginateOfClassName = 'paginate_of';
	var paginatePageClassName = 'paginate_page';
	var paginateInputClassName = 'paginate_input';

	$.fn.dataTableExt.oPagination.input = {
		'fnInit': function (oSettings, nPaging, fnCallbackDraw) {
			//create left div
			var divLeft = document.createElement('div');
			divLeft.className = "item-left";
			var link = document.createElement('a');
			link.className = "linkDirectory";
			divLeft.appendChild(link);
			//end create div left

			//create right div
			var divRight = document.createElement('div');
			divRight.className = "item-right";
			var divClear = document.createElement('div');
			divClear.className = "clear";
			var nPrevious = document.createElement('button');
			var nNext = document.createElement('button');
			var nPage = document.createElement('span');
			var totalPage = document.createElement('span');
			//for select
			var nInput = document.createElement('select');
			nInput.style.display = "inline";

			var language = oSettings.oLanguage.oPaginate;
			var classes = oSettings.oClasses;
            //
			//link.innerHTML = language.leftInfo();
			//nPrevious.innerHTML = language.sPrevious;
			//nNext.innerHTML = language.sNext;

			$(link).html(language.leftInfo());
			$(nPrevious).html(language.sPrevious);
			$(nNext).html(language.sNext);



			nPrevious.className = previousClassName + ' ' + classes.sPageButton;
			nNext.className = nextClassName + ' ' + classes.sPageButton;

			totalPage.className = paginateOfClassName;
			nPage.className = paginatePageClassName;

			if (oSettings.sTableId !== '') {
				nPaging.setAttribute('id', oSettings.sTableId + '_' + paginateClassName);
				nPrevious.setAttribute('id', oSettings.sTableId + '_' + previousClassName);
				nNext.setAttribute('id', oSettings.sTableId + '_' + nextClassName);
			}
			//end create right div
			nPage.innerHTML = language.page;
			nPaging.appendChild(divLeft);

			divRight.appendChild(nPrevious);
			divRight.appendChild(nPage);
			divRight.appendChild(nInput);
			divRight.appendChild(totalPage);
			divRight.appendChild(nNext);

			nPaging.appendChild(divRight);
			nPaging.appendChild(divClear);

			$(nPrevious).click(function() {
				var iCurrentPage = calcCurrentPage(oSettings);
				if (iCurrentPage !== 1) {
					oSettings.oApi._fnPageChange(oSettings, 'previous');
					fnCallbackDraw(oSettings);
				}

				if (iCurrentPage > 2) {
					$(nPrevious).addClass('btn-primary');
					$(nNext).addClass('btn-primary');
				} else {
					$(nPrevious).removeClass('btn-primary');
					$(nNext).addClass('btn-primary');
				}
			});

			$(nNext).click(function() {
				var iCurrentPage = calcCurrentPage(oSettings);
				if (iCurrentPage !== calcPages(oSettings)) {
					oSettings.oApi._fnPageChange(oSettings, 'next');
					fnCallbackDraw(oSettings);
				}

				if (iCurrentPage < calcPages(oSettings) - 1) {
					$(nPrevious).addClass('btn-primary');
					$(nNext).addClass('btn-primary');
				} else {
					$(nNext).removeClass('btn-primary');
					$(nPrevious).addClass('btn-primary');
				}
			});


			
			// Take the brutal approach to cancelling text selection.
			$('span', nPaging).bind('mousedown', function () { return false; });
			$('span', nPaging).bind('selectstart', function() { return false; });

			// If we can't page anyway, might as well not show it.
			var iPages = calcPages(oSettings);
			if (iPages <= 1) {
				$(nPaging).hide();
			}

			

			$(nInput).change(function (e) { // Set DataTables page property and redraw the grid on listbox change event.
				window.scroll(0,0); //scroll to top of page
				if (this.value === "" || this.value.match(/[^0-9]/)) { /* Nothing entered or non-numeric character */
					return;
				}
				var iNewStart = oSettings._iDisplayLength * (this.value - 1);
				if (iNewStart > oSettings.fnRecordsDisplay()) { /* Display overrun */
					oSettings._iDisplayStart = (Math.ceil((oSettings.fnRecordsDisplay() - 1) / oSettings._iDisplayLength) - 1) * oSettings._iDisplayLength;
					fnCallbackDraw(oSettings);
					return;
				}
				oSettings._iDisplayStart = iNewStart;
				fnCallbackDraw(oSettings);
			});
			/* Take the brutal approach to cancelling text selection */
			$('span', nPaging).bind('mousedown', function () {
				return false;
			});
			$('span', nPaging).bind('selectstart', function () {
				return false;
			});
		},

		'fnUpdate': function (oSettings) {

			if (!oSettings.aanFeatures.p) {
				return;
			}
			//update select box
			var iPages = Math.ceil((oSettings.fnRecordsDisplay()) / oSettings._iDisplayLength);
			var iCurrentPage = Math.ceil(oSettings._iDisplayStart / oSettings._iDisplayLength) + 1; 
			/* Loop over each instance of the pager */
			var an = oSettings.aanFeatures.p;
			for (var i = 0, iLen = an.length; i < iLen; i++) {
				var spans = an[i].getElementsByTagName('span');
				var inputs = an[i].getElementsByTagName('select');
				var elSel = inputs[0];
				if(elSel.options.length != iPages) {
					elSel.options.length = 0; //clear the listbox contents
					for (var j = 0; j < iPages; j++) { //add the pages
						var oOption = document.createElement('option');
						oOption.text = j + 1;
						oOption.value = j + 1;
						try {
							elSel.add(oOption, null); // standards compliant; doesn't work in IE
						} catch (ex) {
							elSel.add(oOption); // IE only
						}
					}
					spans[1].innerHTML = "&nbsp;of&nbsp;" + iPages;
				}
			  elSel.value = iCurrentPage;
			}


			var iPages = calcPages(oSettings);
			var iCurrentPage = calcCurrentPage(oSettings);

			var an = oSettings.aanFeatures.p;
			if (iPages <= 1) // hide paging when we can't page
			{
				$(an).hide();
				return;
			}

			var disableClasses = calcDisableClasses(oSettings);
			console.log(disableClasses);
			
			$(an).show();

			// Enable/Disable `prev` button.
			
			$(an).find('.' + previousClassName.replace(new RegExp(' ', 'g'), '.'))
				.removeClass(oSettings.oClasses.sPageButtonDisabled)
				.addClass(disableClasses[previousClassName]);
			
			if($(an).find('.' + previousClassName.replace(new RegExp(' ', 'g'), '.'))
					.hasClass(oSettings.oClasses.sPageButtonDisabled)) {
				$(an).find('.' + previousClassName.replace(new RegExp(' ', 'g'), '.'))
				.removeClass(enableClassName);
			}
			// Enable/Disable `next` button.
			$(an).find('.' + nextClassName.replace(new RegExp(' ', 'g'), '.'))
				.removeClass(oSettings.oClasses.sPageButtonDisabled)
				.addClass(disableClasses[nextClassName]);
			
			if($(an).find('.' + nextClassName.replace(new RegExp(' ', 'g'), '.'))
					.hasClass(oSettings.oClasses.sPageButtonDisabled)) {
				$(an).find('.' + nextClassName.replace(new RegExp(' ', 'g'), '.'))
				.removeClass(enableClassName);
			}
			// Paginate of N pages text
			$(an).children('.' + paginateOfClassName).html(' / ' + iPages);

			// Current page numer input value
			$(an).children('.' + paginateInputClassName).val(iCurrentPage);
		}
	};
})(jQuery);
