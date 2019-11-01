# Amcr Game Launcher

### config.toml

ランチャー本体と同じ階層にconfig.tomlを作成し，以下のような記述を行う．

```toml
[[games]]
title = "Cat Bacuum Cat Flying"
author = "wraikny"
image = "images/CatBacuumCatFlying.png"
cmdWin = "cd games/CatBacuumCatFlying & call CatBacuumCatFlying.exe"
cmdMac = "cd games/CatBacuumCatFlying; mono ./CatBacuumCatFlying.exe"

[[games]]
title = "DOT HOPPER"
author = "negset"
image = "images/DOT HOPPER.png"
cmdWin = "java -jar \"games/DOT HOPPER/DOT HOPPER.jar\""
cmdMac = "java -jar \"games/DOT HOPPER/DOT HOPPER.jar\""

```

### ビルド方法

ルートディレクトリでターミナルを開き，以下のコマンドを叩く．

```sh
./gradlew build
```

/build/libsに.jarファイルが生成される．
