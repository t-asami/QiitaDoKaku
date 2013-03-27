package dokaku1_1
import dokaku1_1.Porker;
import spock.lang.Specification

class PorkerSpock extends Specification {
    def '受け取った文字列を5枚のカードに分割する'() {
        setup:
            def porker = new Porker()

        expect:
            porker.tokenize(card) == cards

        where:
            card           | cards
            'SAHADACAS10'  | ['SA','HA' ,'DA' ,'CA' ,'S10']
            'D3C3C10D10S3' | ['D3','C3' ,'C10','D10','S3' ]
            'SAHADAC9S10'  | ['SA','HA' ,'DA' ,'C9' ,'S10']
            'S8D10HJS10CJ' | ['S8','D10','HJ' ,'S10','CJ' ]
            'S8D10HJS10C1' | ['S8','D10','HJ' ,'S10','C1' ]
            'S8D2HJS10C1'  | ['S8','D2' ,'HJ' ,'S10','C1' ]
    }

    def '対応する役を表示すること。対応する役がない場合は「--」を表示すること'() {
        setup:
            def porker = new Porker()

        expect:
            porker.hand(card) == yaku

        where:
            card           | yaku
            'SAHADACAS10'  | '4K'
            'D3C3C10D10S3' | 'FH'
            'SAHADAC9S10'  | '3K'
            'S8D10HJS10CJ' | '2P'
            'S8D10HJS10C1' | '1P'
            'S8D2HJS10C1'  | '--'
    }
}
