# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

# unreleased

# 0.4.0

Reverse deprecation warnings. Usage of `pureharm-config` is still discouraged in favor of other modules. But, this release will be the last one which will be used by downstream pureharm modules, leaving it to the user to determine how configs ought to be read.

### breaking changes

- support only `RefinedTypeThrow` instead of the generic `RefinedType` sprout implicits. Part of a larger decision for pureharm to support only `Throwable`-like error channels.

### dependency changes

- drop dependency on [pureharm-effects-cats](https://github.com/busymachines/pureharm-effects-cats/releases), depend only on [`cats-effect`](https://github.com/typelevel/cats-effect/releases) `2.4.1`

# 0.3.0

Still EOL, small maintenance release.

### dependency upgrades

- [pureharm-effects-cats](https://github.com/busymachines/pureharm-effects-cats/releases) `0.4.0`

# 0.2.0

This release marks the end of the line for pureharm-config. From now on, pureharm modules will encourage users to depend on `pureharm-config-ciris`, and they will deprecate the usage of `pureharm-config`, and removing it completely starting versions `0.3.x` onwards.

# 0.1.0

Split out from [pureharm](https://github.com/busymachines/pureharm) as of version `0.0.7`.

- cross compiled to Scala 2.13 -- pending support for scala 3.0.0-RC1
- cannot implement automatic derivation support for uses of Sprout encoding, because magnolia macros cannot find the types.

:warning: Removed deprecated methods :warning:

- removed deprecated `ConfigLoader.default` methods.
