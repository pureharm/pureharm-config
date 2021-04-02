# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

# unreleased

# 0.2.0

This release marks the end of the line for pureharm-config. From now on, pureharm modules will encourage users to depend on `pureharm-config-ciris`, and they will deprecate the usage of `pureharm-config`, and removing it completely starting versions `0.3.x` onwards.

### Dependency upgrades
-

# 0.1.0

Split out from [pureharm](https://github.com/busymachines/pureharm) as of version `0.0.7`.

- cross compiled to Scala 2.13 -- pending support for scala 3.0.0-RC1
- cannot implement automatic derivation support for uses of Sprout encoding, because magnolia macros cannot find the types.

:warning: Removed deprecated methods :warning:

- removed deprecated `ConfigLoader.default` methods.
