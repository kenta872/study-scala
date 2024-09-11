# 概要
Scala学習要のリポジトリ

# 技術構成
- scala: 2.13.14
- playframework

# Scala基礎知識

## 型

| 型  | 概要 |
|----|----|
| Int | 32ビット符号付き整数 |
| Long | 64ビット符号付き整数 |
| Byte | 8ビット符号付き整数 |
| Short | 16ビット符号付き整数 |
| Char | 16ビット符号なし整数 |
| Double | 64ビット浮動小数点数 |
| Float | 32ビット浮動小数点数 |
| Boolean | 真偽地 |
| Unit | 意味にある値を持たない型 |
| AnyVal,AnyRef,Any | 型をまとめるための型 |
| Null | JavaのNullのための型 |
| Nothing | すべての型のサブタイプとなる型 |
| String | 文字列を表す型 |
| タプル | 複数の型の組み合わせ |

## クラス
- プライマリコンストラクタ
    ```scala
    // valをつけるとフィールドとして使用できる
    class Sample(val x:Int, val y: Int){}
    ```
    ```scala
    // 修飾子がない場合はクラス内のみで使用できる
    class Sample(x:Int, y: Int){}
    ```
- 継承
    - 1つしか継承できない
    ```scala
    abstract SampleAbstract(){}
    class SampleClass extends SampleAbstract {}
    ```
- トレイト
    - インターフェースのようなもの
    - メソッドも定義できる
    - 複数継承できる
    ```scala
    trait SampleTrait(x:Int, y: Int){}
    class SampleClass with SampleTrait {}
    ```
- ケースクラス
  - hashCode, equals, toStringメソッドを継承しているクラス

## 制御構文
- if式
    ```scala
    if (条件式) 式 else 式
    if (条件式) {
        式
    } else {
        式
    }
    ```
- while式
    ```scala
    while(条件式) {式}
    ```

- for式
    ```scala
    for(x<-1 to 3; y <- 1 until 3){}
    for(e <- List(1,2,3)) println(e)
    ```
- match式
    ```scala
    i match {
        case 0 => "A"
        case 1 => "B"
        case _ => "?"
    }
    ```
## 例外
- try, throw式
    ```scala
    try {
        throw new RuntimeException("error!")      
    } catch {
        case e: Exception => println(e.getMessage)
    }
    ```

## 修飾子
| 修飾子       | 概要                             |
|-----------|--------------------------------|
| private   | 定義したクラスやトレイト内のみ　               |
| protected | 継承先のクラスやトレイト内からもアクセス可能         |
| lazy      | 最初に呼び出されたときに計算され、結果をメモリに保管しておく |
| final     | オーバーライドを防ぐ　                    |
| abstract  | 継承先のクラスでの実装を要求　                |

## Option,Either,Try
- Option
  - 値がないかもしれない
- Either
  - 2つの値のうちどちらかの値
- Try
  - 成功と失敗を扱うクラス。
## コレクション
- Seq
  - 要素が一列に並んだ構造
- Set
  - 集合を意味する
  - 順序付けされない
  - 重複した要素をもてない
- Map
  - key,valueのペアの集合

## 非同期処理
- Future





