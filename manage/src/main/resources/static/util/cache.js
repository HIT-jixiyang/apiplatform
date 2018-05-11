var CACHE = {
    get: function (key) {
        if (typeof key !== 'string') return

        var value = null
        var str = window.localStorage.getItem(key)
        try {
            value = JSON.parse(str)
        } catch (e) {
            value = str
        }

        return value
    },
    set: function (key, value) {
        if (typeof key !== 'string') return
        if (typeof value === 'object') {
            value = JSON.stringify(value)
        }
        try {
            window.localStorage.setItem(key, value)
        } catch (e) {
            console.log(e)
        }
    },
    remove: function (key) {
        if (typeof key !== 'string') return
        window.localStorage.removeItem(key)
    },
    clear: function () {
        window.localStorage.clear()
    }
}