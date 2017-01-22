# README

This is my attempt at using [Kotlin](https://kotlinlang.org) to refactor the videostore code from Martin Fowler's 
[Refactoring](https://martinfowler.com/books/refactoring.html).

The code on the `master` branch is pre-refactoring and the code on the `solution` branch is post-refactoring.

The purpose of this exercise was to become more familiar with Kotlin.

Some of the highlights include:

* Kotlin.
* TDD approach using Spock.
* Immutable classes.
* Custom classes to avoid primitive obsession.
* Fail fast parameter checking.
* No external dependencies other than Spock for testing.

## Requirements

* Kotlin 1.0.6
* Gradle 3.3

## Usage

`gradle test`
