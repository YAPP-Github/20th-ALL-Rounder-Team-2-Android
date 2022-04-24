import org.gradle.api.NamedDomainObjectContainer

inline val <T> NamedDomainObjectContainer<T>.debug: T get() = debug { }
fun <T> NamedDomainObjectContainer<T>.debug(block: T.() -> Unit): T =
    getByName("debug", block::invoke)

inline val <T> NamedDomainObjectContainer<T>.release: T get() = release { }
fun <T> NamedDomainObjectContainer<T>.release(block: T.() -> Unit): T =
    getByName("release", block::invoke)
