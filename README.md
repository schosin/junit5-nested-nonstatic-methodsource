# Broken initialization of parent 

JUnit: 5.12.2

## Setup:

- Parent class initializes a field in a @BeforeEach method
- Non-static @Nested class uses a @MethodSource to a non-static method that
creates arguments based on parent classes field

## Error:

Every @ParameterizedTest using that @MethodSource fails until the first test succeeds/fails that
does not use that @MethodSource.

A @MethodSource to a non-static method fails, when the method tries to access fields of a parent,
which are initialized in a @BeforeEach

## Workarounds:

- Have another @Nested class run before without erorrs (failures are okay)
- Have another @MethodSource in the same @Nested (test method name matters, see Workaround2Test)

## Observations:

- @Nested class name matters, as they sorted order affects outcome
- Test method name matters, see Workaround2Test

## Examples

See various test classes and the comments.
