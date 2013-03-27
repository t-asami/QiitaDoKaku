package dokaku2_1
// http://qiita.com/items/9d80de41903775296ca6

class BinaryFormatImage {
    def toBin = { input ->
        return input.collect {
            Integer.toBinaryString(Integer.parseInt(it, 16)).padLeft(4, '0')
        }.join('')
    }

    // input -> x:xxx
    def trim = { input ->
        def side = input.split(':')[0] as int
        input    = input.split(':')[1]
        return toBin(input)[0..< (side as int) * (side as int)]
    }

    // input -> x:xxx
    def toSquare = { input ->
        def square = []
        def elm    = []
        def side   = input.split(':')[0] as int
        square = trim(input).split(/(?!^)(?=\d)/) as List

        return square.collate(side)
    }

    def rotate = { input ->
        return input.reverse().transpose()
    }

    def answer = { input ->
        def side   = input.split(':')[0]
        def square = toSquare(input)
        def bin    = rotate(square).flatten()
        def hex    = bin.collate(4).collect {
                        Integer.toHexString(
                            Integer.parseInt(it.join('').padRight(4, '0'), 2)
                        )
                    }.join('')
        return "${side}:${hex}"
    }
}
