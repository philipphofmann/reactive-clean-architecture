package com.muffls.testing

inline fun <reified TException : Throwable> expectException(block: () -> Unit): TException =
    expectException(TException::class.java, block) as TException

@Suppress("TooGenericExceptionCaught", "TooGenericExceptionThrown")
inline fun expectException(exceptionClass: Class<*>, block: () -> Unit): Throwable {
    try {
        block()
    } catch (e: Throwable) {
        if (!exceptionClass.isAssignableFrom(e.javaClass)) {
            throw AssertionError(
                "Wrong exception type.\n" +
                    "expected: ${exceptionClass.simpleName}\n" +
                    "actual: ${e::class.simpleName}\n" +
                    "\n" + e.causeTreeToString() + "\n",
                e
            )
        }
        return e
    }
    throw AssertionError(
        "Expected exception of type ${exceptionClass.simpleName}, " +
            "but no exception was thrown"
    )
}

fun Throwable.causeTreeToString(): String {
    val sb = StringBuilder(toString())
    var cause: Throwable? = cause
    var indent = "  "
    while (cause != null) {
        sb.append("\n${indent}caused by: $cause")
        cause = cause.cause
        indent += "  "
    }
    return sb.toString()
}
