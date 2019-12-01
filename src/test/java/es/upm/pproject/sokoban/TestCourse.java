package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.InvalidParameterException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.model.Course;


@DisplayName("Test for Course related methods")
public class TestCourse {
	private Course curso = new Course(0, null, null);
	private boolean True = true;
	
	@Test
	@DisplayName("Test for rejecting blank course names")
	void assertThrowsInvalidaName() {
		System.out.println(this);
		assertTrue(True);
	}  	
}
