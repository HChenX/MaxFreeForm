package com.yifeplayte.maxfreeform.hook.android

import com.github.kyuubiran.ezxhelper.utils.findMethod
import com.github.kyuubiran.ezxhelper.utils.hookMethod
import com.yifeplayte.maxfreeform.hook.BaseHook
import de.robv.android.xposed.XposedBridge

object MaxFreeFormCount : BaseHook() {
    override fun init() {
        try {
            findMethod("com.android.server.wm.MiuiFreeFormStackDisplayStrategy") {
                name == "getMaxMiuiFreeFormStackCount"
            }.hookMethod {
                after { param ->
                    param.result = 256
                }
            }
            XposedBridge.log("MaxFreeForm: Hook getMaxMiuiFreeFormStackCount success!")
        } catch (e: Throwable) {
            XposedBridge.log("MaxFreeForm: Hook getMaxMiuiFreeFormStackCount failed!")
        }
    }
}