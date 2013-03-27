package dokaku2_1
import dokaku2_1.BinaryFormatImage;
import spock.lang.Specification


class BinaryFormatImageSpock extends Specification {
    def '入力を2進数表記にする'() {
        setup:
            def bfi = new BinaryFormatImage()

        expect:
            bfi.toBin(input) == output

        where:
            input       | output
            '5b8'       | '010110111000'
            '8'         | '1000'
            '8'         | '1000'
            '4'         | '0100'
            '1'         | '0001'
            '5d0'       | '010111010000'
            '1234'      | '0001001000110100'
            '22a2a20'   | '0010001010100010101000100000'
            '1234567'   | '0001001000110100010101100111'
            '123456789' | '000100100011010001010110011110001001'
    }

    def '2進数表記で不要な部分を除去する'() {
        setup:
            def bfi = new BinaryFormatImage()

        expect:
            bfi.trim(input) == output

        where:
            input       | output
            '3:5b8'     | '010110111'
            '1:8'       | '1'
            '2:8'       | '1000'
            '2:4'       | '0100'
            '2:1'       | '0001'
            '3:5d0'     | '010111010'
            '4:1234'    | '0001001000110100'
            '5:22a2a20' | '0010001010100010101000100'
            '5:1234567' | '0001001000110100010101100'
    }

    def '正方行列にする'() {
        setup:
            def bfi = new BinaryFormatImage()

        expect:
            bfi.toSquare(input) == output

        where:
            input   | output
            '1:8'   | [['1']]
            '2:8'   | [['1','0'],['0','0']]
            '2:4'   | [['0','1'],['0','0']]
            '2:1'   | [['0','0'],['0','1']]
            '3:5b8' | [['0','1','0'],['1','1','0'],['1','1','1']]
            '3:5d0' | [['0','1','0'],['1','1','1'],['0','1','0']]
    }

    def '回転する'() {
        setup:
            def bfi = new BinaryFormatImage()

        expect:
            bfi.rotate(input) == output

        where:
            input                                       | output
            [['0','1','0'],['1','1','0'],['1','1','1']] | [['1','1','0'],['1','1','1'],['1','0','0']]
    }

    def '正解を求める'() {
        setup:
            def bfi = new BinaryFormatImage()

        expect:
            bfi.answer(input) == output

        where:
            input                     | output
            '3:5b8'                   | '3:de0'
            '1:8'                     | '1:8'
            '2:8'                     | '2:4'
            '2:4'                     | '2:1'
            '2:1'                     | '2:2'
            '3:5d0'                   | '3:5d0'
            '4:1234'                  | '4:0865'
            '5:22a2a20'               | '5:22a2a20'
            '5:1234567'               | '5:25b0540'
            '6:123456789'             | '6:09cc196a6'
            '7:123456789abcd'         | '7:f1a206734b258'
            '7:fffffffffffff'         | '7:ffffffffffff8'
            '7:fdfbf7efdfbf0'         | '7:ffffffffffc00'
            '8:123456789abcdef1'      | '8:f0ccaaff78665580'
            '9:112233445566778899aab' | '9:b23da9011d22daf005d40'
    }
}
