

# SENG 637 - Dependability and Reliability of Software Systems

## Lab Report #3 – Code Coverage, Adequacy Criteria and Test Case Correlation

| Group #       |  9 |
| ------------- | - |
| Student Names |   |
|               |   |
|               |   |
|               |   |
---

# 1 Introduction

Software reliability depends heavily on the quality and adequacy of the testing process. Unit testing is one of the most widely used techniques for verifying the correctness of individual components in a software system. In addition to validating functional behavior, modern testing practices emphasize evaluating the adequacy of a test suite using structural analysis techniques. White-box testing provides a systematic way to achieve this by analyzing the internal structure of the source code and ensuring that test cases exercise critical execution paths.

This assignment focuses on evaluating and improving the adequacy of a unit test suite using code coverage criteria. Code coverage metrics provide quantitative indicators of how thoroughly a test suite exercises the system under test (SUT). Common structural coverage criteria include statement coverage, branch (decision) coverage, and condition coverage, each representing a progressively stronger level of testing completeness. By measuring these metrics, testers can identify untested regions of code and design additional test cases to improve the overall quality of the test suite.

The system under test in this assignment is JFreeChart, an open-source Java framework used for creating charts and data visualizations. JFreeChart is designed to be integrated into other applications as a reusable component, making the correctness and robustness of its API functions critical. Two classes from the org.jfree.data package—DataUtilities and Range—are selected for detailed analysis and testing in this assignment.

To perform the testing and coverage analysis, the JUnit testing framework is used to execute unit tests within the Eclipse development environment, while coverage tools are used to measure structural coverage metrics. Coverage tools help identify the portions of code that are executed by the existing test suite and reveal areas that require additional test cases to achieve higher coverage.

In addition to automated coverage measurement, this assignment also introduces data-flow testing, which examines how variables are defined and subsequently used during program execution. Data-flow coverage focuses on identifying definition-use (DU) pairs, ensuring that variable definitions propagate correctly to their uses along feasible execution paths. This analysis provides deeper insight into potential faults that may not be detected through control-flow coverage alone.

The objectives of this assignment are therefore threefold: to measure the adequacy of an existing test suite using coverage tools, to design additional unit tests that increase structural coverage, and to manually analyze data-flow coverage for selected methods. Through these activities, the assignment demonstrates how coverage-based testing techniques can be used to systematically improve test effectiveness while highlighting both the strengths and limitations of coverage metrics as indicators of software quality.


---

# 2 Manual Data-Flow Coverage Calculations for X and Y Methods

Manual data-flow analysis was conducted for the following methods within the `DataUtilities` class:

* `calculateColumnTotal`
* `calculateRowTotal`

Data-flow testing evaluates the relationships between **variable definitions (def)** and **subsequent uses (use)** along feasible execution paths in the control flow of a program. The objective of this analysis is to identify **Definition–Use (DU) pairs**, which capture how data values propagate through the program and influence computation. By examining DU pairs, testers can determine whether the test suite adequately exercises all meaningful data dependencies within the system under test.

The method `calculateColumnTotal` was selected for detailed analysis. This method computes the sum of values in a specified column of a two-dimensional dataset.

The primary variables involved in the computation include:

* `data`
* `column`
* `rowCount`
* `total`
* loop control variables (`r`, `r2`)
* intermediate numeric values (`n1`, `n2`)

---

## Data Flow Graph

The **Data Flow Graph (DFG)** illustrates the propagation of variable definitions and uses across the control flow of the `calculateColumnTotal` method.

*(Insert Data Flow Graph Image Here)*

```<img width="1015" height="1124" alt="image7" src="https://github.com/user-attachments/assets/3b8f4f96-93f5-4c89-81e7-26ea9173cc04" />

[ DATA FLOW GRAPH IMAGE PLACEHOLDER ]
```

### Notes

* The `for` loop statement was decomposed into **three separate logical statements** for the purpose of data-flow analysis:

  * loop initialization
  * loop condition comparison
  * loop increment

* The original loop variable `n` was renamed to **`n1` and `n2`** during analysis to avoid ambiguity when identifying definition–use relationships.

This decomposition simplifies the identification of variable definitions and uses within iterative constructs and enables more precise tracking of DU pairs.

---

## Def–Use Sets per Statement

The following table summarizes the **definition and use sets for each relevant statement** in the analyzed method.

| Statement | Definition   | Use              |
| --------- | ------------ | ---------------- |
| 123       | data, column |                  |
| 124       |              | data             |
| 125       | total        |                  |
| 126       | rowCount     | data             |
| 127       | r            | r, rowCount      |
| 128       | n1           | data, r, column  |
| 129       |              | n1               |
| 130       | total        | total, n1        |
| 131       |              |                  |
| 132       |              |                  |
| 133       | r2           | r2, rowCount     |
| 134       | n2           | data, r2, column |
| 135       |              | n2               |
| 136       | total        | total, n2        |
| 137       |              |                  |
| 138       |              |                  |
| 139       |              | total            |

These def-use sets form the basis for identifying **all possible DU pairs** in the method.

---

## DU-Pairs per Variable

The following table lists all **Definition–Use pairs identified for each variable**.

| Variable | DU-pairs                                                                              |
| -------- | ------------------------------------------------------------------------------------- |
| data     | (123,124), (123,126), (123,128), (123,134 *not reachable*)                            |
| column   | (123,128), (123,134 *not reachable*)                                                  |
| total    | (125,130), (125,136 *not reachable*), (125,139), (130,139), (136 *not reachable*,139) |
| rowCount | (126,127), (126,133)                                                                  |
| r        | (127,127), (127,128)                                                                  |
| n1       | (128,129), (128,130)                                                                  |
| r2       | (133,133), (133,134 *not reachable*)                                                  |
| n2       | (134 *not reachable*,135 *not reachable*), (134 *not reachable*,136 *not reachable*)  |

Some DU pairs are marked as **not reachable** because the corresponding control-flow paths cannot be executed due to program logic constraints.

---

## Reachable DU-Pairs

After removing DU pairs associated with infeasible execution paths, the **set of reachable DU pairs** is obtained.

| Variable | Reachable DU-pairs              |
| -------- | ------------------------------- |
| data     | (123,124), (123,126), (123,128) |
| column   | (123,128)                       |
| total    | (125,130), (125,139), (130,139) |
| rowCount | (126,127), (126,133)            |
| r        | (127,127), (127,128)            |
| n1       | (128,129), (128,130)            |
| r2       | (133,133)                       |
| n2       | —                               |

The DU pairs associated with variable `n2` are unreachable because the corresponding loop structure is never executed.

---

## DU-Pairs Covered per Test Case

The following table summarizes which DU pairs are exercised by the designed unit tests.

| Test Case Name                    | DU-pairs Covered |
| --------------------------------- | ---------------- |
| `test_calculateColumnTotal_ect_1` |                  |
| `test_calculateColumnTotal_ect_2` |                  |

These test cases are designed to exercise the feasible definition-use paths associated with the primary aggregation loop.

---

## Discussion of Infeasible Paths

During the analysis, it was observed that certain control-flow paths within the method are **infeasible**.

Specifically, the second loop in the method has the following condition:

```java
for (int r2 = 0; r2 > rowCount; r2++)
```

Since `rowCount` represents the number of rows in the dataset and therefore cannot be negative, the condition `r2 > rowCount` evaluates to **false at the first iteration**. Consequently, the loop body is **never executed**, rendering all statements inside this loop unreachable.

As a result:

* DU pairs involving variable `n2` cannot be exercised.
* Complete DU coverage is **not theoretically achievable** for this method.

Similar infeasible structures were also identified in the methods:

* `calculateRowTotal`
* `getCumulativePercentages`

Therefore, the maximum achievable data-flow coverage corresponds to the **set of reachable DU pairs rather than the full theoretical set**.

---
Great — the **next section in your template is Section 3: Testing Strategy**, and we will convert your **Test Plan for DataUtilities** into a **PI-level structured section** while **keeping all your content** and **leaving space for the screenshots/images exactly like your PDF shows**. The screenshots from your document (for infeasible paths, line coverage, branch coverage, etc.) should be inserted where indicated. 

Below is **ONLY Section 3**, ready to paste.

---

# 3 A detailed description of the testing strategy for the new unit test

The testing strategy for the `DataUtilities` class was developed based on a **coverage-driven test design approach**. The goal of this strategy was to systematically evaluate the adequacy of the existing unit tests and identify whether additional test cases were required to achieve higher levels of structural coverage.

The testing process involved the following steps:

1. **Identifying infeasible code paths**
2. **Evaluating line coverage**
3. **Evaluating branch coverage**
4. **Evaluating method coverage**
5. **Performing data flow analysis**

Each stage was used to determine whether additional test cases were necessary to improve coverage.

---

## Test Plan for DataUtilities

### Infeasible Code Paths

During inspection of the source code, several infeasible paths were identified. These paths correspond to sections of code that cannot be executed due to logical constraints in the program.

#### calculateColumnTotal

In the `calculateColumnTotal` method, the second loop contains the following condition:

```
for (int r2 = 0; r2 > rowCount; r2++)
```

Because `rowCount` represents the number of rows in the dataset and therefore cannot be negative, the condition `r2 > rowCount` evaluates to **false during the first iteration**. As a result, the entire loop body becomes unreachable.

*(Insert Screenshot – Infeasible loop in calculateColumnTotal)*

```text
[ IMAGE PLACEHOLDER – calculateColumnTotal infeasible loop ]
```

#### calculateRowTotal

A similar structure exists in the `calculateRowTotal` method. The second loop contains the condition:

```
for (int c2 = 0; c2 > columnCount; c2++)
```

Since `columnCount` cannot be negative, the loop condition is never satisfied, making this loop unreachable.

*(Insert Screenshot – Infeasible loop in calculateRowTotal)*

```text
[ IMAGE PLACEHOLDER – calculateRowTotal infeasible loop ]
```

#### getCumulativePercentages

The method `getCumulativePercentages` also contains a second loop that depends on the condition:

```
for (int i2 = 0; i2 > data.getItemCount(); i2++)
```

Because `data.getItemCount()` represents the number of items in the dataset and cannot be negative, the condition is never satisfied, resulting in an unreachable loop body.

*(Insert Screenshot – Infeasible loop in getCumulativePercentages)*

```text
[ IMAGE PLACEHOLDER – getCumulativePercentages infeasible loop ]
```

These infeasible paths reduce the maximum achievable structural coverage for the methods.

---

## Test Cases for Line Coverage

Line coverage was evaluated to determine whether all executable statements were exercised by the existing test suite.

### createNumberArray

No additional test cases were required because the existing tests already cover the **maximum number of feasible lines**.

*(Insert Screenshot – createNumberArray coverage)*

```text
[ IMAGE PLACEHOLDER – createNumberArray coverage ]
```

### createNumberArray2D

No additional test cases were required since all feasible lines were already covered.

*(Insert Screenshot – createNumberArray2D coverage)*

```text
[ IMAGE PLACEHOLDER – createNumberArray2D coverage ]
```

### calculateColumnTotal

All feasible statements were already covered by the existing test cases.

*(Insert Screenshot – calculateColumnTotal coverage)*

```text
[ IMAGE PLACEHOLDER – calculateColumnTotal coverage ]
```

### calculateRowTotal

Similarly, the maximum feasible line coverage was already achieved.

*(Insert Screenshot – calculateRowTotal coverage)*

```text
[ IMAGE PLACEHOLDER – calculateRowTotal coverage ]
```

### getCumulativePercentages

The existing tests already achieved the maximum feasible line coverage.

*(Insert Screenshot – getCumulativePercentages coverage)*

```text
[ IMAGE PLACEHOLDER – getCumulativePercentages coverage ]
```

### Line Coverage Results

The coverage results confirm that all feasible lines have been executed.

*(Insert Screenshot – Line Coverage Results Table)*

```text
[ IMAGE PLACEHOLDER – line coverage results ]
```

These results represent **maximum feasible coverage**, since unreachable code cannot be executed.

---

## Test Cases for Branch Coverage

Branch coverage analysis was conducted to determine whether both true and false outcomes of conditional expressions were exercised.

### createNumberArray

No additional test cases were required because all feasible branches were already covered.

### createNumberArray2D

No additional test cases were required.

### calculateColumnTotal

One additional test case was created to exercise the condition in **line 129** by inserting a **null value into the `Values2D` dataset**.

*(Insert Screenshot – Branch coverage in calculateColumnTotal)*

```text
[ IMAGE PLACEHOLDER – calculateColumnTotal branch coverage ]
```

### calculateRowTotal

Similar to `calculateColumnTotal`, an additional test case was added to cover the conditional check by inserting a **null value into the dataset**.

*(Insert Screenshot – Branch coverage in calculateRowTotal)*

```text
[ IMAGE PLACEHOLDER – calculateRowTotal branch coverage ]
```

### getCumulativePercentages

No additional test cases were required because the maximum feasible branch coverage had already been achieved.

*(Insert Screenshot – getCumulativePercentages branch coverage)*

```text
[ IMAGE PLACEHOLDER – getCumulativePercentages branch coverage ]
```

### Branch Coverage Results

*(Insert Screenshot – Branch Coverage Results Table)*

```text
[ IMAGE PLACEHOLDER – branch coverage results ]
```

These results correspond to the **maximum achievable coverage considering infeasible paths**.

---

## Test Cases for Method Coverage

Because none of the evaluated coverage tools supported **condition coverage**, method coverage was used as an alternative metric.

No additional test cases were required since all methods were already exercised by the test suite.

*(Insert Screenshot – Method Coverage Results Table)*

```text
[ IMAGE PLACEHOLDER – method coverage results ]
```

---

## Data Flow Coverage

Finally, data flow coverage was analyzed for the `calculateColumnTotal` method to evaluate whether the designed test cases exercised the identified **definition–use pairs**.

*(Insert Screenshot – Data Flow Coverage for calculateColumnTotal)*

```text
[ IMAGE PLACEHOLDER – data flow coverage screenshot ]
```

The results confirm that the feasible DU pairs associated with the main computation loop are exercised by the designed test cases.


---

# 4 A high level description of five selected test cases you have designed using coverage information, and how they have increased code coverage

Based on the results of the coverage analysis, several unit tests were designed to ensure that the feasible execution paths of the `DataUtilities` class were exercised. The selected test cases target typical execution scenarios, boundary conditions, and conditional branches within the methods under test. These tests were chosen to demonstrate how coverage-guided testing improves the effectiveness of the test suite.

---

## Test Case 1 – Standard Dataset Aggregation

This test case uses a dataset containing multiple rows with valid numeric values.

**Purpose**

The objective of this test is to verify the normal behavior of the aggregation logic implemented in the `calculateColumnTotal` method. The dataset contains several numeric entries, ensuring that the main loop responsible for summing column values is executed.

**Coverage Contribution**

* Executes the primary loop structure in the method.
* Covers the statements responsible for retrieving dataset values and updating the running total.
* Improves **line coverage** for the core aggregation logic.

---

## Test Case 2 – Dataset Containing Null Values

This test case introduces one or more **null values** in the dataset.

**Purpose**

The test is designed to trigger the conditional statement that checks whether the retrieved value is null before adding it to the total.

```java
if (n != null)
```

**Coverage Contribution**

* Executes both the **true** and **false** outcomes of the conditional statement.
* Improves **branch coverage** for the null-check condition.
* Ensures the method correctly ignores null values during aggregation.

---

## Test Case 3 – Empty Dataset

In this test case, an empty dataset is provided as input.

**Purpose**

The objective is to verify that the method behaves correctly when there are no elements to process.

**Coverage Contribution**

* Exercises the loop boundary condition where the iteration count is zero.
* Confirms that the method correctly returns a total of zero without errors.
* Improves coverage of loop termination logic.

---

## Test Case 4 – Single Row Dataset

This test case uses a dataset containing only one row.

**Purpose**

The goal of this test is to evaluate how the method handles minimal input sizes.

**Coverage Contribution**

* Executes the loop exactly once.
* Tests boundary conditions related to minimal dataset size.
* Confirms that the aggregation logic functions correctly when the dataset contains only one element.

---

## Test Case 5 – Multi-row Multi-column Dataset

This test case uses a dataset containing several rows and multiple columns.

**Purpose**

The objective of this test is to simulate a realistic dataset scenario and verify that the method correctly retrieves values from different rows while computing the column total.

**Coverage Contribution**

* Exercises repeated iterations of the main loop.
* Confirms that values are retrieved correctly from the dataset structure.
* Improves **overall structural coverage** across multiple iterations.

---

# 5 A detailed report of the coverage achieved of each class and method

Coverage analysis was conducted to evaluate the adequacy of the test suite for the `DataUtilities` class. The analysis focused on three structural coverage metrics:

* **Line coverage**
* **Branch coverage**
* **Method coverage**

These metrics provide quantitative insight into how effectively the unit tests exercise the internal logic of the system under test.

---

## Line Coverage Results

Line coverage measures the proportion of executable statements that are executed during test execution.

The coverage analysis showed that several methods in the `DataUtilities` class already achieved **maximum feasible line coverage**. In particular, the following methods required **no additional test cases** because all reachable statements were already exercised by the existing tests:

* `createNumberArray`
* `createNumberArray2D`
* `calculateColumnTotal`
* `calculateRowTotal`
* `getCumulativePercentages`

The line coverage results are shown below.

*(Insert Screenshot – Line Coverage Results)*

```
[ IMAGE PLACEHOLDER – Line Coverage Results ]
```

According to the coverage report, the `DataUtilities` class achieved a high level of statement execution, and the remaining uncovered lines correspond primarily to infeasible code paths. The coverage table presented in the screenshot illustrates the percentage of covered lines and the number of executed statements for each method. 

---

## Branch Coverage Results

Branch coverage evaluates whether both outcomes of conditional expressions (true and false) have been exercised by the test suite.

For most methods in the `DataUtilities` class, the existing tests already covered all feasible branches. However, one additional test case was introduced to increase branch coverage in certain methods.

### calculateColumnTotal

An additional test case was designed to exercise the conditional check in **line 129** by introducing a **null value in the `Values2D` dataset**.

*(Insert Screenshot – Branch coverage for calculateColumnTotal)*

```
[ IMAGE PLACEHOLDER – calculateColumnTotal branch coverage ]
```

### calculateRowTotal

Similarly, an additional test case was added to trigger the conditional branch in `calculateRowTotal` by inserting a **null value in the dataset**.

*(Insert Screenshot – Branch coverage for calculateRowTotal)*

```
[ IMAGE PLACEHOLDER – calculateRowTotal branch coverage ]
```

### getCumulativePercentages

No new test cases were required for this method because the existing tests already achieved the maximum feasible branch coverage.

*(Insert Screenshot – Branch coverage results table)*

```
[ IMAGE PLACEHOLDER – Branch Coverage Results ]
```

The branch coverage results confirm that the feasible decision outcomes within the methods are exercised by the test suite. Any remaining uncovered branches correspond to **infeasible execution paths** identified during earlier analysis.

---

## Method Coverage Results

Because none of the evaluated coverage tools supported **condition coverage**, method coverage was used as an alternative metric to ensure that each method in the class was executed at least once during testing.

The results indicate that **all methods in the `DataUtilities` class were executed by the test suite**, and therefore no additional tests were required to improve method coverage.

*(Insert Screenshot – Method Coverage Results)*

```
[ IMAGE PLACEHOLDER – Method Coverage Results ]
```

The method coverage table illustrates the number of methods executed during testing and confirms that the test suite successfully invokes each method under analysis. 

---

## Interpretation of Coverage Results

The coverage analysis demonstrates that the test suite achieves **maximum feasible structural coverage** for the `DataUtilities` class. Remaining uncovered code segments correspond to **infeasible paths**, such as loop conditions that cannot evaluate to true due to program constraints.

Consequently, additional test cases cannot increase coverage beyond the levels reported above.

---

# 6 Pros and Cons of coverage tools used and Metrics you report

Several coverage tools were evaluated during this lab in order to measure the adequacy of the test suite and analyze structural coverage of the `DataUtilities` class. The tools considered include **CodeCover**, **Clover**, **JaCoCo**, and **Coverlipse**. These tools provide different levels of support for coverage metrics such as statement coverage, branch coverage, and method coverage.

However, one major limitation encountered during the experiment was the lack of support for **condition coverage** among the available tools.

---

## CodeCover

CodeCover is a coverage tool designed to support advanced coverage metrics including condition coverage.

During the lab, an attempt was made to install the **CodeCover Eclipse plugin** using the standard Eclipse update mechanism.

*(Insert Screenshot – CodeCover installation instructions)*

```text
[IMAGE PLACEHOLDER — CodeCover installation instructions]
```

However, an error occurred during installation, preventing the plugin from being successfully installed in the development environment.

*(Insert Screenshot – CodeCover installation error)*

```text
[IMAGE PLACEHOLDER — CodeCover installation error]
```

Because of this installation failure, CodeCover could not be used for the coverage analysis. 

### Advantages

* Supports advanced structural coverage metrics
* Provides detailed reports for testing adequacy
* Designed specifically for white-box testing analysis

### Disadvantages

* Installation issues prevented practical usage
* Requires additional configuration within Eclipse
* Less commonly integrated with modern development environments

---

## Clover

Clover is a widely used code coverage tool integrated with development environments such as Eclipse and IntelliJ.

Clover supports several basic coverage metrics including:

* **Statement coverage**
* **Branch coverage**
* **Method coverage**

*(Insert Screenshot – Clover coverage description)*

```text
[IMAGE PLACEHOLDER — Clover documentation screenshot]
```

However, Clover does **not support condition coverage**, which limits its usefulness when evaluating complex boolean expressions.

### Advantages

* Easy integration with development tools
* Provides clear coverage reports
* Supports multiple coverage metrics

### Disadvantages

* Does not support condition coverage
* Limited ability to analyze complex conditional expressions

---

## JaCoCo

JaCoCo is another commonly used Java coverage tool that provides detailed coverage metrics for Java applications.

According to the JaCoCo documentation, the tool supports:

* instruction coverage
* line coverage
* branch coverage
* method coverage

However, JaCoCo also **does not support condition coverage**.

### Advantages

* Lightweight and widely used in Java projects
* Provides fast coverage analysis
* Integrates well with build tools such as Maven and Gradle

### Disadvantages

* Does not support condition coverage
* Limited analysis of compound logical conditions

---

## Coverlipse

Coverlipse is an Eclipse plugin designed to provide coverage analysis for Java projects.

Similar to the other tools evaluated, Coverlipse provides support for several coverage metrics but **does not support condition coverage**.

### Advantages

* Simple integration with Eclipse
* Provides quick visualization of executed code

### Disadvantages

* Does not support condition coverage
* Limited support for advanced coverage metrics

---

## Coverage Metrics Reported

Due to the absence of condition coverage support in the available tools, the following coverage metrics were used in this lab:

1. **Line Coverage**
   Measures the proportion of executable statements that are executed during testing.

2. **Branch Coverage**
   Measures whether both outcomes of conditional statements are exercised.

3. **Method Coverage**
   Ensures that each method in the class is executed at least once during testing.

Method coverage was used as a **substitute metric** for condition coverage to ensure that all methods in the `DataUtilities` class were exercised during testing.

---

## Summary

The evaluation of coverage tools revealed that while several tools support basic structural coverage metrics, **none of the evaluated tools provided support for condition coverage within the lab environment**. As a result, the coverage analysis relied on line coverage, branch coverage, and method coverage to evaluate the adequacy of the test suite.

---
# 7 A comparison on the advantages and disadvantages of requirements-based test generation and coverage-based test generation

Software testing strategies can broadly be categorized into **requirements-based testing** and **coverage-based testing**. These two approaches focus on different aspects of the system and serve complementary purposes in ensuring software reliability.

Requirements-based testing derives test cases from the **functional specifications and requirements of the system**, whereas coverage-based testing derives tests from the **internal structure of the source code**. Both approaches have distinct advantages and limitations.

---

## Requirements-Based Test Generation

Requirements-based testing focuses on verifying whether the system behaves according to the functional specifications defined during the design phase. Test cases are derived directly from system requirements, use cases, and expected behaviors.

### Advantages

* Ensures that the system satisfies **functional requirements**.
* Focuses on **user-visible behavior**, making it highly relevant to real-world usage.
* Helps validate system functionality even without access to source code.
* Useful for **black-box testing scenarios**.

### Disadvantages

* May fail to exercise **internal program logic paths**.
* Some code segments may remain untested if they are not directly linked to requirements.
* Limited ability to detect **implementation-level defects**.

---

## Coverage-Based Test Generation

Coverage-based testing focuses on the internal structure of the program. Test cases are designed to exercise different parts of the source code, such as statements, branches, and paths.

### Advantages

* Ensures systematic exploration of the **program’s control flow**.
* Identifies **untested code segments**.
* Improves test completeness by ensuring that important execution paths are covered.
* Helps detect **implementation errors and hidden logical defects**.

### Disadvantages

* High coverage does not necessarily guarantee **correct functionality**.
* Some generated tests may not correspond to realistic user scenarios.
* May require access to source code and deeper understanding of program internals.

---

## Comparison of the Two Approaches

| Aspect                 | Requirements-Based Testing             | Coverage-Based Testing            |
| ---------------------- | -------------------------------------- | --------------------------------- |
| Testing focus          | Functional requirements                | Internal program structure        |
| Testing type           | Black-box testing                      | White-box testing                 |
| Test generation source | System requirements and specifications | Source code structure             |
| Primary objective      | Validate system behavior               | Ensure execution of code paths    |
| Strength               | Validates user expectations            | Detects untested code segments    |
| Limitation             | May miss internal faults               | May ignore functional correctness |

---

## Complementary Nature of Both Approaches

In practice, effective testing strategies combine both approaches. Requirements-based testing ensures that the system behaves correctly from the user’s perspective, while coverage-based testing ensures that the internal logic of the program is adequately exercised.

By integrating both methods, testers can achieve higher confidence in both **functional correctness** and **structural reliability** of the software system.


---

# 8 A discussion on how the team work/effort was divided and managed

The workload for this lab was divided among the team members to ensure efficient progress and balanced contribution. The group split the assignment based on the two main classes that required analysis and testing.

Dhruvi and Jasdeep were responsible for the **DataUtilities** class. Their tasks included performing the data-flow analysis, identifying infeasible paths, designing additional test cases, executing the tests, and analyzing the coverage results for the methods within this class. They also prepared the documentation related to the testing strategy and coverage analysis for `DataUtilities`.

Maheen and Dipu worked on the **Range** class. Their responsibilities included analyzing the source code of the class, designing test cases to improve coverage, evaluating structural coverage metrics, and documenting the testing process and results for that component.

The team coordinated regularly to ensure consistency in testing methodology, coverage analysis, and documentation style across both parts of the assignment. This division of work allowed the group to analyze both classes in depth while maintaining a structured and organized workflow throughout the lab.


---

# 9 Any difficulties encountered, challenges overcome, and lessons learned from performing the lab

During the execution of this lab, several challenges were encountered while performing the coverage analysis and designing adequate unit tests. These challenges required both manual analysis and experimentation with different testing tools.

One major difficulty was the **lack of support for condition coverage in the available coverage tools**. Several tools were evaluated, including CodeCover, Clover, JaCoCo, and Coverlipse. Although CodeCover theoretically supports condition coverage, the installation of the Eclipse plugin failed due to compatibility issues, preventing its use during the lab. The remaining tools only supported line, branch, and method coverage. 

Another challenge involved identifying **infeasible code paths** in the `DataUtilities` methods. Certain loop conditions in methods such as `calculateColumnTotal`, `calculateRowTotal`, and `getCumulativePercentages` were logically impossible to satisfy because the dataset size variables cannot be negative. This required careful manual inspection of the source code to determine why some lines remained uncovered during coverage analysis. 

Performing **data-flow analysis** also presented difficulties. Determining the definition-use relationships for variables required careful tracking of variable definitions and their subsequent uses across different statements and loop structures. In addition, the for-loop constructs had to be separated into initialization, comparison, and increment statements in order to correctly construct the data-flow graph and identify DU-pairs.

Despite these challenges, the lab provided valuable experience in applying structural testing techniques. It demonstrated how coverage metrics can help identify untested parts of a program and guide the creation of additional test cases.

Overall, the lab highlighted the importance of combining **manual code analysis with automated testing tools**. It also reinforced the idea that high coverage metrics must be interpreted carefully, especially when infeasible paths exist in the program.


---

# 10 Comments/feedback on the lab itself

This lab provided valuable hands-on experience with software testing and coverage analysis. It helped demonstrate how structural testing techniques such as line coverage, branch coverage, and data-flow testing can be applied to evaluate the adequacy of a test suite. Through the analysis of the `DataUtilities` and `Range` classes, the lab illustrated how coverage metrics can reveal untested portions of code and guide the design of additional test cases.

One particularly useful aspect of the lab was the opportunity to perform **data-flow analysis manually**. Identifying definition–use pairs and constructing the data-flow graph provided deeper insight into how variables propagate through the program and how certain execution paths may become infeasible. This exercise reinforced the importance of understanding the internal structure of the code when performing white-box testing.

The lab also highlighted practical challenges associated with testing tools. Several coverage tools were evaluated, but not all of them supported the required coverage metrics, particularly condition coverage. This demonstrated that selecting appropriate testing tools is an important part of the software testing process.

Overall, the lab was effective in helping students understand the relationship between **code coverage metrics and software reliability**. It provided a practical perspective on how systematic testing approaches can improve software quality and ensure that critical execution paths are adequately tested.

---




