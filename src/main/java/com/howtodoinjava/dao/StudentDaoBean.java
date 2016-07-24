package com.howtodoinjava.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDaoBean implements StudentDao {
	private static List<Student> list= new ArrayList<Student>();
	static {
		// 模拟数据<br> list = new ArrayList<Student>();
		for (int i = 1; i <= 110023; i++) {
			Student st = new Student(i, i, "NAME" + i, new Date(), "ClassName" + i, i % 2 == 0 ? '0' : '1');
			list.add(st);
		}
	}

	@Override
	public List<Object> getStudent(int page, int rows) {
		System.out.println(page + " : " + rows);
		List<Object> students = new ArrayList<Object>();
		for (int i = page * rows; i < rows * (page + 1); i++) {
			students.add(list.get(i));
		}
		// 获取数据<br> return students;
		return students;
	}

	@Override
	public int getTotalPages(int rows) {
		int size = list.size();
		// int pages = size%rows==0? size/rows: size/rows+1;
		return size;
		// 获取总的条数<br> }

	}
}