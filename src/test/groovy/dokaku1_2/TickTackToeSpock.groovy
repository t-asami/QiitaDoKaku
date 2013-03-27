package dokaku1_2
import dokaku1_2.TickTackToe;
import spock.lang.Specification

class TickTackToeSpock extends Specification {
    def 'isBingo'() {
        setup:
            def ttt = new TickTackToe()

        expect:
            ttt.isBingo(input) == output

        where:
            input                | output
            ['1', '2', '3', '4'] | true
            ['4', '5', '6']      | true
            ['7', '8', '9']      | true
            ['1', '4', '7']      | true
            ['2', '5', '8']      | true
            ['3', '6', '9']      | true
            ['1', '5', '9']      | true
            ['9', '5', '1']      | true
            ['3', '5', '7']      | true
            ['1', '2', '4']      | false
            ['4', '5', '7']      | false
            ['1', '8', '9']      | false
            ['2', '4', '7']      | false
            ['3', '5', '8']      | false
            ['1', '6', '9']      | false
            ['2', '5', '9']      | false
            ['2', '5', '7']      | false
    }

    def 'x won.'() {
        setup:
            def ttt = new TickTackToe()

        expect:
            ttt.start(input) == output

        where:
            input         | output
            '79538246'    | 'x won.'
            '35497162193' | 'x won.'
            '61978543'    | 'x won.'
            '254961323121'| 'x won.'
            '6134278187'  | 'x won.'
    }

    def 'Foul : x won.'() {
        setup:
            def ttt = new TickTackToe()

        expect:
            ttt.start(input) == output

        where:
            input         | output
            '4319581'     | 'Foul : x won.'
            '9625663381'  | 'Foul : x won.'
            '7975662'     | 'Foul : x won.'
            '2368799597'  | 'Foul : x won.'
            '18652368566' | 'Foul : x won.'
    }

    def 'o won.'() {
        setup:
            def ttt = new TickTackToe()

        expect:
            ttt.start(input) == output

        where:
            input         | output
            '965715'      | 'o won.'
            '38745796'    | 'o won.'
            '371929'      | 'o won.'
            '758698769'   | 'o won.'
            '42683953'    | 'o won.'
    }

    def 'Foul : o won.'() {
        setup:
            def ttt = new TickTackToe()

        expect:
            ttt.start(input) == output

        where:
            input         | output
            '618843927'   | 'Foul : o won.'
            '36535224'    | 'Foul : o won.'
            '882973'      | 'Foul : o won.'
            '653675681'   | 'Foul : o won.'
            '9729934662'  | 'Foul : o won.'
    }

    def 'Draw game.'() {
        setup:
            def ttt = new TickTackToe()

        expect:
            ttt.start(input) == output

        where:
            input         | output
            '972651483927'| 'Draw game.'
            '5439126787'  | 'Draw game.'
            '142583697'   | 'Draw game.'
            '42198637563' | 'Draw game.'
            '657391482'   | 'Draw game.'
    }
}
