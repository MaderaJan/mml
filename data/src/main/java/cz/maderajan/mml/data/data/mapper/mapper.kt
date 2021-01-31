package cz.maderajan.mml.data.data.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}

interface MapperWithParam<I, O, T> {
    fun map(input: I, param: T): O
}

internal fun <I, O> Mapper<I, O>.forLists(): (List<I>) -> List<O> {
    return { list -> list.mapNotNull { item -> map(item) } }
}

internal fun <I, I1, I2> pairMapperOf(
    firstMapper: Mapper<I, I1>,
    secondMapper: Mapper<I, I2>
): suspend (List<I>) -> List<Pair<I1, I2>> = { from ->
    from.map { value ->
        firstMapper.map(value) to secondMapper.map(value)
    }
}