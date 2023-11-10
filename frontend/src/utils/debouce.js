/**
 * 防抖函数
 * @param {function} func 需要执行的函数
 * @param {Number} wait 延迟时间
 * @param {number} immediate 是否立即执行
 */
function debouce(func, wait = 0, immediate) {
    // 判断传递的参数类型
    if(typeof func !== 'function') {
        throw new TypeError('debounce的第一个参数类型为funtion')
    }

    let timer;
    return (...arg) => {
        // 清除定时器
        timer && clearTimeout(timer)
        // 立即执行
        if(immediate) {
            const callNow = !timer
            timer = setTimeout(() => {
                func(...arg)
            }, wait)
            callNow && func(...arg)
        } else {
            // 非立即执行
            timer = setTimeout(() => {
                func(...arg)
            }, wait)
        }
    }
}
export default debouce