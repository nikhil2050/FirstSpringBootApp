package com.nk.junit5.javabrains;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

public class MathUtilsTest {

	/**
	 * assertEquals(expected, actual)
	 * assertArrayEquals(expectedArray, actualArray)
	 * assertIterableEquals(expectedArray, actualArray)
	 * 
	 * Hooks:
	 * 	@BeforeAll
	 * 	@AfterAll
	 * 	@BeforeEach
	 * 	@AfterEach
	 * 
	 * @DisplayName		: Assign name for testcase
	 * @Disabled		: Disable a case
	 * @EnabledOnOS		: Disable for some OS
	 * @Tag				: Enable, disable from Junit run configurations
	 * 
	 * @Nested			: For grouping testcases
	 * @RepeatedTest(3)	: Repeat a testcase multiple times. 'RepetitionInfo' gives current and total iterations
	 * 
	 * TestInfo
	 * TestReporter
	 */
	
	MathUtils mu;
	
	@BeforeAll
	static void classLevelInit() {
		System.out.println("BeforeAll..");
	}
	
	@BeforeEach
	void init() {
		System.out.println("BeforeEach..");
		mu = new MathUtils();
	}

	@Test
	@Disabled
	void disabledFromTest() {
		fail("Failure case");
	}

	@Nested
	class DivisionTesting {
		@Test
		@EnabledOnOs(OS.WINDOWS)
		void testDivide() {
			System.out.println("\tTestcase1..");
			int expected = 2;
			double actual = mu.divide(8,4);
			assertEquals(expected, actual, "My divide method should divide nos.");
		}
		
		@Test
		@DisplayName("diviDe bY 0 test")
		void testDivide2() {
			System.out.println("\tTestcase2..");
			assertThrows(ArithmeticException.class, () -> mu.divide(4, 0), "ExceptioN expecteD");
		}
	
		@RepeatedTest(3)
		void testDivide3(RepetitionInfo repetitionInfo) {
			System.out.println("\tTestcase3.. Repetiting:"+repetitionInfo.getCurrentRepetition() +" of "+ repetitionInfo.getTotalRepetitions());
			assertEquals(2, mu.divide(8, 4));
		}
		
		@Test
		void testDivide4() {
			System.out.println("\tTestcase4..");
			assertAll(
					() -> assertEquals(4, mu.divide(8,2)),
					() -> assertEquals(2, mu.divide(8,4))
					);
			assertThrows(ArithmeticException.class, () -> mu.divide(4, 0), "ExceptioN expecteD");
		}
	}
	
	@Test
	@Tag("TagAlwaysTrue")
	void testTrue(TestInfo testInfo, TestReporter testReporter) {
		System.out.println("\nRunning test info :: "+ testInfo+"\n");
		testReporter.publishEntry("This is testReporter output...");	// Can be used in init()
		assertTrue(true);
	}
}
