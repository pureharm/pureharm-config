# pureharm-config

Version 0.2.0 is end of the line for `pureharm-config` it is being superseded by `pureharm-config-ciris` which dramatically alters the way configs are dealt with. So it's no small change. Starting with versions `0.2.0` downstream pureharm moduls (e.g, db-slick, db-doobie, aws, etc) will deprecate the usage of the `ConfigLoader` companion objects for their configurations and encourage users to use `ciris` to read the configs on user side with the prefered method. Starting with version `0.3.0` no downstream module will be coupled with the concrete method of reading configs anymore, it will all be left in user-land, and recommended to be done w/ `pureharm-config-ciris`.

The latter approach has many advantages, including easier publishing of the whole pureharm thing.

See [changelog](./CHANGELOG.md).

## modules

The available modules are.

- `"com.busymachines" %% s"pureharm-config" % "0.2.0"`. Which has these as its main dependencies:
    - [pureconfig](https://github.com/pureconfig/pureconfig/releases) `0.14.0`
    - [pureharm-core-anomaly](https://github.com/busymachines/pureharm-core/releases) `0.2.0`
    - [pureharm-core-sprout](https://github.com/busymachines/pureharm-core/releases) `0.2.0`
    - [pureharm-effects-cats](https://github.com/busymachines/pureharm-effects-cats/releases) `0.2.0`

## usage

Under construction. See [release notes](https://github.com/busymachines/pureharm-core/releases) and tests for examples.

## Copyright and License

All code is available to you under the Apache 2.0 license, available
at [http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0) and also in
the [LICENSE](./LICENSE) file.
