# Comapre-Image-UsingSelenium

# Selenium

[![CI - Ruby](https://github.com/SeleniumHQ/selenium/actions/workflows/ci-ruby.yml/badge.svg)](https://github.com/SeleniumHQ/selenium/actions/workflows/ci-ruby.yml)
[![CI - Python](https://github.com/SeleniumHQ/selenium/actions/workflows/ci-python.yml/badge.svg)](https://github.com/SeleniumHQ/selenium/actions/workflows/ci-python.yml)
[![CI - JavaScript](https://github.com/SeleniumHQ/selenium/actions/workflows/ci-javascript.yml/badge.svg)](https://github.com/SeleniumHQ/selenium/actions/workflows/ci-javascript.yml)
[![CI - Java](https://github.com/SeleniumHQ/selenium/actions/workflows/ci-java.yml/badge.svg)](https://github.com/SeleniumHQ/selenium/actions/workflows/ci-java.yml)

<a href="https://selenium.dev"><img src="https://selenium.dev/images/selenium_logo_square_green.png" width="180" alt="Selenium"/></a>

Selenium is an umbrella project encapsulating a variety of tools and
libraries enabling web browser automation. Selenium specifically
provides an infrastructure for the [W3C WebDriver specification](https://w3c.github.io/webdriver/)
— a platform and language-neutral coding interface compatible with all
major web browsers.

The project is made possible by volunteer contributors who've
generously donated thousands of hours in code development and upkeep.

Selenium's source code is made available under the [Apache 2.0 license](https://github.com/SeleniumHQ/selenium/blob/trunk/LICENSE).

## Documentation

Narrative documentation:

* [User Manual](https://selenium.dev/documentation/)

API documentation:

* [C#](https://seleniumhq.github.io/selenium/docs/api/dotnet/)
* [JavaScript](https://seleniumhq.github.io/selenium/docs/api/javascript/)
* [Java](https://seleniumhq.github.io/selenium/docs/api/java/index.html)
* [Python](https://seleniumhq.github.io/selenium/docs/api/py/)
* [Ruby](https://seleniumhq.github.io/selenium/docs/api/rb/)

## Pull Requests

Please read [CONTRIBUTING.md](https://github.com/SeleniumHQ/selenium/blob/trunk/CONTRIBUTING.md)
before submitting your pull requests.

## Requirements

* [Bazelisk](https://github.com/bazelbuild/bazelisk), a Bazel wrapper that automatically downloads
  the version of Bazel specified in `.bazelversion` file and transparently passes through all
  command-line arguments to the real Bazel binary.
* The latest version of the [Java 11 OpenJDK](https://openjdk.java.net/)
* `java` and `jar` on the `$PATH` (make sure you use `java` executable from JDK but not JRE).
  * To test this, try running the command `javac`. This command won't exist if you only have the JRE
  installed. If you're met with a list of command-line options, you're referencing the JDK properly.
* [Python 3.7+](https://www.python.org/downloads/) and `python` on the `PATH`
* [Ruby 3+](https://www.ruby-lang.org/en/documentation/installation/) and `ruby` on the `PATH`
* [The tox automation project](http://tox.readthedocs.org/) for Python: `pip install tox`
* macOS users:
  * Install the latest version of Xcode including the command-line tools. This command should work `xcode-select --install` 
  * Apple Silicon Macs should add `build --host_platform=//:rosetta` to their `.bazelrc.local` file. We are working
  to make sure this isn't required in the long run.
* Windows users:
  *  Latest version of [Visual Studio](https://www.visualstudio.com/) with command line tools and build tools installed
  * `BAZEL_VS` environment variable should point to the location of the build tools,
     e.g. `C:\Program Files (x86)\Microsoft Visual Studio\2019\BuildTools`
  * `BAZEL_VC` environment variable should point to the location of the command line tools,
     e.g. `C:\Program Files (x86)\Microsoft Visual Studio\2019\Community\VC`
  * `BAZEL_VC_FULL_VERSION` environment variable should contain the version of the installed command line tools,
     e.g. `14.27.29110`
  * A detailed setup guide can be seen on Jim Evan's [post](http://jimevansmusic.blogspot.com/2020/04/setting-up-windows-development.html)
  * If the Jim's blog instructions were followed, also make sure `C:\tools\msys65\usr\bin` is on the `PATH`.

### Internet Explorer Driver

If you plan to compile the
[IE driver](https://github.com/SeleniumHQ/selenium/wiki/InternetExplorerDriver),
you also need:

* [Visual Studio 2008](https://www.visualstudio.com/)
* 32 and 64-bit cross compilers

The build will work on any platform, but the tests for IE will be
skipped silently if you are not building on Windows.

## Building

### Bazel

[Bazel](https://bazel.build/) was built by the fine folks at Google. Bazel manages dependency
downloads, generates the Selenium binaries, executes tests, and does it all rather quickly.

More detailed instructions for getting Bazel running are below, but if you can successfully get
the java and javascript folders to build without errors, you should be confident that you have the
correct binaries on your system.

### Before Building

Ensure that you have Firefox installed and the latest
[`geckodriver`](https://github.com/mozilla/geckodriver/releases/) on your `$PATH`.
You may have to update this from time to time.

### Common Build Targets

#### Java

<details>
<summary>Click to see Java Build Steps</summary>

To build the most commonly-used modules of Selenium from source, execute this command from the root
project folder:

```sh
bazel build java/...
```

If you want to test you can run then you can do so by running the following command

```sh
bazel test //java/... --test_size_filters=small,medium,large --test_tag_filters=<browser>
```

The `test_size_filters` argument takes small, medium, large. Small are akin to unit tests,
medium is akin to integration tests, and large is akin to end to end tests.

The `test_tag_filters` allow us to pass in browser names and a few different tags that we can
find in the code base.
  
To build the Grid deployment jar, run this command:

```sh
bazel build grid
```

The log will show where the output jar is located.
  
</details>

#### JavaScript
<details>
<summary>Click to see JavaScript Build Steps</summary>

If you want to build all the JavaScript code you can run:

```sh
bazel build javascript/...
```

To build the NodeJS bindings you will need to run:

```sh
bazel build //javascript/node/selenium-webdriver
```

To run the tests run:

```sh
bazel test //javascript/node/selenium-webdriver:tests
```

You can pass in the environment variable `SELENIUM_BROWSER` with the name of the browser.

To publish to NPM run:

```sh
bazel run //javascript/node/selenium-webdriver:selenium-webdriver.publish
```
</details>

#### Python
<details>
<summary>Click to see Python Build Steps</summary>

If you want to build the python bindings run:

```sh
bazel build //py:selenium
```

To run the tests run:

```sh
bazel test //py:test-<browsername>
```

If you add `--//common:pin_browsers` it will download the browsers and drivers for you to use.
  
To install locally run:
  
```sh
bazel build //py:selenium-wheel
pip install bazel-bin/py/selenium-*.whl
```

To publish run:

```sh
bazel build //py:selenium-wheel
twine upload bazel-bin/py/selenium-*.whl
```
</details>

####  Ruby
<details>
<summary>Click to see Ruby Build Steps</summary>

To build the Ruby code run:

```sh
bazel build //rb/...
```
</details>

####  .NET
<details>
<summary>Click to see .NET Build Steps</summary>

To build the .NET code run:

```sh
bazel build //dotnet/...
```

Also
```sh
bazel build //dotnet/test/common:chrome
```
  
</details>
