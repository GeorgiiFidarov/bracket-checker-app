package org.brackets.checker.sberContest;

import org.brackets.checker.sberContest.controllers.BracketController;
import org.brackets.checker.sberContest.request.CheckBracketsRequest;
import org.brackets.checker.sberContest.response.IsCorrectResponse;
import org.brackets.checker.sberContest.service.BracketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class BracketControllerTest {

	private BracketController bracketController;
	@Mock
	private BracketService bracketService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		bracketController = new BracketController(bracketService);
	}

	@Test
	public void testValidTextWithNonEmptyBrackets() {
		CheckBracketsRequest requestBody = new CheckBracketsRequest("Это (валидный) пример.");
		when(bracketService.isValidBrackets("Это (валидный) пример.")).thenReturn(true);
		ResponseEntity<Object> responseEntity = bracketController.checkBrackets(requestBody);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(responseEntity.getBody() instanceof IsCorrectResponse);
		IsCorrectResponse isCorrectResponse = (IsCorrectResponse) responseEntity.getBody();
		assertTrue(isCorrectResponse.isCorrect());
	}

	@Test
	public void testTextWithEmptyBrackets() {
		CheckBracketsRequest requestBody = new CheckBracketsRequest("Пустые () скобки.");
		when(bracketService.isValidBrackets("Пустые () скобки.")).thenReturn(false);
		ResponseEntity<Object> responseEntity = bracketController.checkBrackets(requestBody);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(responseEntity.getBody() instanceof IsCorrectResponse);
		IsCorrectResponse isCorrectResponse = (IsCorrectResponse) responseEntity.getBody();
		assertFalse(isCorrectResponse.isCorrect());
	}

	@Test
	public void testTextWithMismatchedBrackets() {
		CheckBracketsRequest requestBody = new CheckBracketsRequest("Не согласованные (скобки).)");
		when(bracketService.isValidBrackets("Не согласованные (скобки).)")).thenReturn(false);
		ResponseEntity<Object> responseEntity = bracketController.checkBrackets(requestBody);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(responseEntity.getBody() instanceof IsCorrectResponse);
		IsCorrectResponse isCorrectResponse = (IsCorrectResponse) responseEntity.getBody();
		assertFalse(isCorrectResponse.isCorrect());
	}

	@Test
	public void testEmptyText() {
		CheckBracketsRequest requestBody = new CheckBracketsRequest("");
		ResponseEntity<Object> responseEntity = bracketController.checkBrackets(requestBody);
		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
	}

	@Test
	public void testNullText() {
		CheckBracketsRequest requestBody = new CheckBracketsRequest(null);
		ResponseEntity<Object> responseEntity = bracketController.checkBrackets(requestBody);
		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
	}

	@Test
	public void testEmptyTextWithSpaces() {
		CheckBracketsRequest requestBody = new CheckBracketsRequest("     ");
		ResponseEntity<Object> responseEntity = bracketController.checkBrackets(requestBody);
		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
	}

	@Test
	public void testValidTextWithSpaces() {
		CheckBracketsRequest requestBody = new CheckBracketsRequest("Правильный текст (валид).");
		when(bracketService.isValidBrackets("Правильный текст (валид).")).thenReturn(true);
		ResponseEntity<Object> responseEntity = bracketController.checkBrackets(requestBody);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(responseEntity.getBody() instanceof IsCorrectResponse);
		IsCorrectResponse isCorrectResponse = (IsCorrectResponse) responseEntity.getBody();
		assertTrue(isCorrectResponse.isCorrect());
	}

	@Test
	public void testValidTextWithCommas() {
		CheckBracketsRequest requestBody = new CheckBracketsRequest("Текст с (правильными),(скобками).");
		when(bracketService.isValidBrackets("Текст с (правильными),(скобками).")).thenReturn(true);
		ResponseEntity<Object> responseEntity = bracketController.checkBrackets(requestBody);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(responseEntity.getBody() instanceof IsCorrectResponse);
		IsCorrectResponse isCorrectResponse = (IsCorrectResponse) responseEntity.getBody();
		assertTrue(isCorrectResponse.isCorrect());
	}

	@Test
	public void testInvalidTextWithCommas() {
		CheckBracketsRequest requestBody = new CheckBracketsRequest("Текст с  (кривыми), (, скобками).");
		when(bracketService.isValidBrackets("Текст с  (кривыми), (, скобками).")).thenReturn(false);
		ResponseEntity<Object> responseEntity = bracketController.checkBrackets(requestBody);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(responseEntity.getBody() instanceof IsCorrectResponse);
		IsCorrectResponse isCorrectResponse = (IsCorrectResponse) responseEntity.getBody();
		assertFalse(isCorrectResponse.isCorrect());
	}

	@Test
	public void testValidTextWithNestedBrackets() {
		CheckBracketsRequest requestBody = new CheckBracketsRequest("Нормальный (добротый (текст со скобками)).");
		when(bracketService.isValidBrackets("Нормальный (добротый (текст со скобками)).")).thenReturn(true);
		ResponseEntity<Object> responseEntity = bracketController.checkBrackets(requestBody);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(responseEntity.getBody() instanceof IsCorrectResponse);
		IsCorrectResponse isCorrectResponse = (IsCorrectResponse) responseEntity.getBody();
		assertTrue(isCorrectResponse.isCorrect());
	}

	@Test
	public void testInvalidTextWithNestedBrackets() {
		CheckBracketsRequest requestBody = new CheckBracketsRequest("Чем черт (не шутит (еще один) тест)).");
		when(bracketService.isValidBrackets("Чем черт (не шутит (еще один) тест)).")).thenReturn(false);
		ResponseEntity<Object> responseEntity = bracketController.checkBrackets(requestBody);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertTrue(responseEntity.getBody() instanceof IsCorrectResponse);
		IsCorrectResponse isCorrectResponse = (IsCorrectResponse) responseEntity.getBody();
		assertFalse(isCorrectResponse.isCorrect());
	}
}
