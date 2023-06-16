package com.yifeplayte.maxfreeform.hook.hooks.android

import com.github.kyuubiran.ezxhelper.ClassUtils.loadClass
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHook
import com.github.kyuubiran.ezxhelper.HookFactory.`-Static`.createHooks
import com.github.kyuubiran.ezxhelper.finders.MethodFinder.`-Static`.methodFinder
import com.yifeplayte.maxfreeform.hook.hooks.BaseHook

object UnlockFreeformQuantityLimit : BaseHook() {
    override fun init() {
        val clazzMiuiFreeFormStackDisplayStrategy = loadClass("com.android.server.wm.MiuiFreeFormStackDisplayStrategy")
        clazzMiuiFreeFormStackDisplayStrategy.methodFinder().filter {
            name in setOf("getMaxMiuiFreeFormStackCount", "getMaxMiuiFreeFormStackCountForFlashBack")
        }.toList().createHooks {
            returnConstant(256)
        }
        clazzMiuiFreeFormStackDisplayStrategy.methodFinder().filterByName("shouldStopStartFreeform").first()
            .createHook {
                returnConstant(false)
            }
    }
}