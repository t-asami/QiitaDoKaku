package dokaku3_1

import org.junit.Ignore;

import spock.lang.Specification

class BallCountSpock extends Specification {
    def 'ストライクが３つになったらアウトが増え、ストライクとボールがゼロになる。'() {
        setup:
            def bc = new BallCount()

        expect:
            bc.setInput(input) == output

        where:
            input    | output
            'sss'    | '010,020,100'
            'bbbsss' | '001,002,003,013,023,100'
            'bbbssb' | '001,002,003,013,023,000'
    }

    def 'ボールが4つになったらフォアボールになり、ストライクとボールがゼロになる。アウトは増えない。'() {
        setup:
            def bc = new BallCount()

        expect:
            bc.setInput(input) == output

        where:
            input    | output
            'bbbb'   | '001,002,003,000'
            'bbbssb' | '001,002,003,013,023,000'
    }

    def 'ヒットを打ったらストライクとボールがゼロになる。アウトは増えない。'() {
        setup:
        def bc = new BallCount()

        expect:
            bc.setInput(input) == output

        where:
            input  | output
            'h'    | '000'
            'ssh'  | '010,020,000'
            'bbbh' | '001,002,003,000'
    }

    def 'ピッチャーフライを打ったらストライクとボールがゼロになり、アウトが増える。'() {
        setup:
        def bc = new BallCount()

    expect:
        bc.setInput(input) == output

    where:
        input   |output
        'sp'    |'010,100'
        'bp'    |'001,100'
        'bbbssp'|'001,002,003,013,023,100'
    }

    def 'アウトが3つになったら、アウト・ストライク・ボール全てゼロになる。'() {
        setup:
        def bc = new BallCount()

    expect:
        bc.setInput(input) == output

    where:
        input     |output
        'ppp'     |'100,200,000'
        'ppsp'    |'100,200,210,000'
        'ppbsbsbs'|'100,200,201,211,212,222,223,000'
    }

    def 'ファウルの場合、もともとストライクが1以下の場合はストライクが増え、ストライクが2の場合には変化なし。'() {
        setup:
        def bc = new BallCount()

        expect:
            bc.setInput(input) == output

        where:
            input | output
            'f'   | '010'
            'ff'  | '010,020'
            'fff' | '010,020,020'
    }

    def '入力と出力の例'() {
        setup:
            def bc = new BallCount()

        expect:
            bc.setInput(input) == output

        where:
            input            | output
            's'              |'010'
            'sss'            |'010,020,100'
            'bbbb'           |'001,002,003,000'
            'ssbbbb'         |'010,020,021,022,023,000'
            'hsbhfhbh'       |'000,010,011,000,010,000,001,000'
            'psbpfpbp'       |'100,110,111,200,210,000,001,100'
            'ppp'            |'100,200,000'
            'ffffs'          |'010,020,020,020,100'
            'ssspfffs'       |'010,020,100,200,210,220,220,000'
            'bbbsfbppp'      |'001,002,003,013,023,000,100,200,000'
            'sssbbbbsbhsbppp'|'010,020,100,101,102,103,100,110,111,100,110,111,200,000,100'
            'ssffpffssp'     |'010,020,020,020,100,110,120,200,210,000'
    }
}
