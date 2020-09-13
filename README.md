# code-analyzer

An IntelliJ IDEA plugin that allows to analyze a selected section of code (**Java language only**)in an editor. Plugin can get Abstract syntax tree (AST), the number of declared variables, the number of calls to variable values, and the number of exceptions thrown. The goal of the plugin is to explore the internal structure of your source code.

The plugin creates a special button in Tool bar for quick navigation.

## Installation

* **Linux** / **Mac**
* JDK 8

### Install from disk
* ```cd code-analyzer```
* ```./gradlew jar``` 
* Go to ```Settings-> Plugins-> Install plugin from disk```
* Select .jar from the second step
* Restart IntelliJ IDEA

## How to use

Select the code in the editor, click the button next to the Build button in your IDE

### Technical description

The implementation is based on [PSI](https://jetbrains.org/intellij/sdk/docs/basics/architectural_overview/psi.html). This is a set of interfaces in the IntelliJ IDEA, responsible for parsing source files and building syntactic and semantic models from them.

After code selecting, PsiElement instances are created. They contain various information about the elements to which they correspond. For example, to find the number of exceptions, it is enough to find all ```PsiThrowStatement```.

It is also important to note that some information in the original PSI tree is redundant for the construction of the AST. For example, it contains grouping brackets that shouldn't be present in the tree.
