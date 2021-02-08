package cz.maderajan.mml.commonutil

interface IEffect

class SuccessEffect : IEffect
object LoadingEffect : IEffect
object ReadyEffect : IEffect
class ErrorEffect(val message: Int) : IEffect