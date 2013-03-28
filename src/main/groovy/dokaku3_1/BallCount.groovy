package dokaku3_1
// http://qiita.com/items/ebd8a56b41711ba459f9

class ScoreBoard {
    def _ballCount   = 0
    def _strikeCount = 0
    def _outCount    = 0

    // クリア処理
    private def _clearBallCount = {
        _ballCount = 0
    }

    private def _clearStrikeCount = {
        _strikeCount = 0
    }

    private def _clearOutCount = {
        _outCount = 0
    }

    private def _change = {
        _clearBallCount()
        _clearStrikeCount()
        _clearOutCount()
    }

    // OSBの順番なので注意
    private def _printCount = {
        return "${_outCount}${_strikeCount}${_ballCount}"
    }

    // インクリメント処理
    def incrementOutCount = {
        if (_outCount == 2) {
            _change()
        } else {
            _outCount++
            _clearBallCount()
            _clearStrikeCount()
        }

        _printCount()
    }

    def incrementBallCount = {
        if (_ballCount == 3) {
            _clearBallCount()
            _clearStrikeCount()
        } else {
            _ballCount++
        }

        _printCount()
    }

    def incrementStrikeCount = {
        if (_strikeCount == 2) {
            incrementOutCount()
        } else {
            _strikeCount++
        }

        _printCount()
    }

    def incrementFoul = {
        if (_strikeCount < 2) {
            _strikeCount++
        }

        _printCount()
    }

    def incrementHit = {
        _clearBallCount()
        _clearStrikeCount()

        _printCount()
    }
}

class BallCount {
    def scoreBoard = new ScoreBoard()

    def setBallCount = {
        switch(it) {
            case 's':
                return scoreBoard.incrementStrikeCount()
            case 'b':
                return scoreBoard.incrementBallCount()
            case 'f':
                return scoreBoard.incrementFoul()
            case 'h':
                return scoreBoard.incrementHit()
            case 'p':
                return scoreBoard.incrementOutCount()
        }
    }

    def setInput = { input ->
        def result    = []
        input.collect() {
            result << setBallCount(it)
        }

        return result.join(',')
    }
}
