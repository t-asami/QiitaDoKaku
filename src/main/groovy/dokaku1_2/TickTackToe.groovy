package dokaku1_2
// http://nabetani.sakura.ne.jp/hena/1/

class TickTackToe {
    final def bingo = [['1', '2', '3'], ['4', '5', '6'], ['7', '8', '9'],
                       ['1', '4', '7'], ['2', '5', '8'], ['3', '6', '9'],
                       ['1', '5', '9'], ['3', '5', '7']]

    def isBingo = { list ->
        if (list.size() < 3) return false
        return bingo.any {
            list.containsAll(it)
        }
    }

    def start = { input ->
        def senko = []
        def kouko = []
        input.toList().eachWithIndex { v, i -> if (!(i % 2)) senko << v }
        input.toList().eachWithIndex { v, i -> if (i % 2)    kouko << v }

        def rest       = '1'..'9'
        def obtained   = []
        def senkoKekka = []
        def koukoKekka = []
        def loopEnd    = Math.max(senko.size(), kouko.size())

        for (def i = 0; i < loopEnd; i++) {
            // obtainedにsenko[i]が既にあれば,反則負け
            if (obtained.indexOf(senko[i]) != -1) {
                return 'Foul : x won.'
            } else {
                senkoKekka << senko[i]
                rest       -= senko[i]
                obtained   += senko[i]

                // 揃っているかチェック
                if (isBingo(senkoKekka.sort())) {
                    return 'o won.'
                }
            }

            // 9マス埋まったら終了
            if (rest.size() < 1) break

            // obtainedにkouko[i]が既にあれば,反則負け
            if (obtained.indexOf(kouko[i]) != -1) {
                return 'Foul : o won.'
            } else {
                koukoKekka << kouko[i]
                rest       -= kouko[i]
                obtained   += kouko[i]

                // 揃っているかチェック
                if (isBingo(koukoKekka.sort())) {
                    return 'x won.'
                }
            }
        }

        return 'Draw game.'
    }
}
