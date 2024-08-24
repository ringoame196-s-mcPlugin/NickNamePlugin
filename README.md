# NickNamePlugin

## プラグイン説明
ニックネームを設定をすることができる



https://github.com/user-attachments/assets/63ebe785-d574-48e5-8076-7cbed96dbefe



## コマンド
/nickname <ニックネーム> (<対象のプレイヤー名>※OPのみ実行可能)

## 使い方
- 自分のニックネームを設定する - /nickname <ニックネーム>
- 他のプレイヤーのニックネームを設定する - /nickname <ニックネーム> <対象のプレイヤー名>

## タグ
nickNameTag - このタグを持っているとニックネームを設定することが可能になる <br>
一回使うと自動でタグ削除される

## パーミッション
| パーミッション名           | ニックネームの設定 | 他プレイヤーのニックネームを設定する |
|--------------------|-----------|--------------------|
| `nickname.setting` | ◯         | ×                  |
| `nickname.admin`   | ◯         | ◯                  |

## configファイル
- nickNameTag - タグ設定 タグをつけていると1回だけコマンドを使用することができる(使うと自動でtag削除される)

## 開発環境
- Minecraft Version : 1.20.1
- Kotlin Version : 1.6.10
