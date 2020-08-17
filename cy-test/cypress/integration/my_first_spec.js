/// <reference-types="Cypress" />

describe("My first test suite", () => {
  it("test url works", () => {
    cy.visit("https://demo.realworld.io/#/");
  });
  it("test signup exists", () => {
    cy.contains("a.nav-link", "Sign up").click();
  });
});
