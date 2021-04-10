# pureharm-config

Version `0.4.0` is end of the line for `pureharm-config` it is being superseded by `pureharm-config-ciris` which dramatically alters the way configs are dealt with. So it's no small change. Starting with versions `0.3.0` (`0.4.0` in some cases) downstream pureharm modules (e.g, db-slick, db-doobie, aws, etc) will deprecate the usage of the `ConfigLoader` companion objects for their configurations and encourage users to use `ciris` to read the configs on user side with the prefered method.

`pureharm-config` will still be released w/ maintenance releases, but it should be used in user-code, and will no longer be pulled in by future pureharm-modules.

The latter approach has many advantages, including easier publishing of the whole pureharm thing.

See [changelog](./CHANGELOG.md).

## modules

The available modules are.

- `"com.busymachines" %% s"pureharm-config" % "0.4.0"`. Which has these as its main dependencies:
  - [cats-effect](https://github.com/typelevel/cats-effect/releases) `2.4.1`
  - [pureconfig](https://github.com/pureconfig/pureconfig/releases) `0.14.0`
  - [pureharm-core-anomaly](https://github.com/busymachines/pureharm-core/releases) `0.2.0`
  - [pureharm-core-sprout](https://github.com/busymachines/pureharm-core/releases) `0.2.0`

## usage

Under construction. See [release notes](https://github.com/busymachines/pureharm-core/releases) and tests for examples.

## Copyright and License

All code is available to you under the Apache 2.0 license, available
at [http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0) and also in
the [LICENSE](./LICENSE) file.
