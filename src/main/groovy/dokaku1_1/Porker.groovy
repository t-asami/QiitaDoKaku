package dokaku1_1
// http://qiita.com/items/cbc3af152ee3f50a822f
class Porker {
    final String FOUR_CARD  = '4K'
    final String FULL_HOUSE = 'FH'
    final String THREE_CARD = '3K'
    final String TWO_PAIR   = '2P'
    final String ONE_PAIR   = '1P'
    final String NO_PAIR    = '--'

    def tokenize(def card) {
        // 先頭以外で右側がスートの位置で分割
        card.split(/(?!^)(?=S)|(?!^)(?=H)|(?!^)(?=D)|(?!^)(?=C)/)
    }

    def hand(def card) {
        def cards = tokenize(card) as List
        // 各カードを1文字目以降（ランク）でグループ化
        def group = cards.groupBy { it[1..<it.size()] }
        return makeYaku(group)
    }

    def makeYaku(def group) {
        // ランク毎にグループ化した各グループの枚数を計算
        def groupNum = group.collect { it.value.size() }

        switch(groupNum.max()) {
            // 4カード
            case 4:
                return FOUR_CARD

            // グループの最大枚数が3の場合は, フルハウスかスリーカード
            case 3:
                // グループの最大枚数が3の場合で, 1ペアがあればフルハウス
                if (groupNum.findAll { it == 2 }.size()) {
                    return FULL_HOUSE
                } else {
                    return THREE_CARD
                }

            // グループの最大枚数が2の場合は, 1ペアか2ペア
            case 2:
                // 1ペアの数が2なら2ペア, 1なら1ペア
                if (groupNum.findAll { it == 2 }.size() == 2) {
                    return TWO_PAIR
                } else {
                    return ONE_PAIR
                }
            // ノーペア
            case 1:
                return NO_PAIR

            default:
                return 'unknown'
        }
    }
}
