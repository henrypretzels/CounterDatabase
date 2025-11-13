base URL:
https://raw.githubusercontent.com/ByMykel/CSGO-API/main/public/api/pt-BR

skins.json:
{
id: "skin-65604",
name: "Desert Eagle | Urban DDPAT",
description:
"As expensive as it is powerful, the Desert Eagle is an iconic pistol that is difficult to master but surprisingly accurate at long range. It has been painted using a Digital Disruptive Pattern (DDPAT) hydrographic.\\n\\nBy the time you're close enough to notice the pixels it's already too late",
weapon: {
id: "weapon_deagle",
weapon_id: 1,
name: "Desert Eagle",
},
category: {
id: "csgo_inventory_weapon_category_pistols",
name: "Pistols",
},
pattern: {
id: "hy_ddpat_urb",
name: "Urban DDPAT",
},
min_float: 0.06,
max_float: 0.8,
rarity: {
id: "rarity_uncommon_weapon",
name: "Industrial Grade",
color: "#5e98d9",
},
stattrak: false,
souvenir: true,
paint_index: "17",
wears: [
{
id: "SFUI_InvTooltip_Wear_Amount_0",
name: "Factory New",
},
// ...
],
collections: [
{
id: "collection-set-overpass",
name: "The Overpass Collection",
image:
"https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/set_icons/set_overpass_png.png",
},
],
crates: [
{
id: "crate-4028",
name: "ESL One Cologne 2014 Overpass Souvenir Package",
image:
"https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/weapon_cases/crate_esl14_promo_de_overpass_png.png",
},
// ...
],
team: {
id: "both",
name: "Both Teams",
},
legacy_model: true,
image:
"https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/default_generated/weapon_deagle_hy_ddpat_urb_light_png.png",
},
// ...
]

stickers.json:
{
id: "sticker-75",
name: "Sticker | Titan | Katowice 2014",
description:
"This item commemorates the The 2014 EMS One Katowice CS:GO Championship.This sticker can be applied to any weapon you own and can be scraped to look more worn. You can scrape the same sticker multiple times, making it a bit more worn each time, until it is removed from the weapon.",
rarity: {
id: "rarity_rare",
name: "High Grade",
color: "#4b69ff",
},
crates: [
{
id: "crate-4015",
name: "EMS Katowice 2014 Legends",
image:
"https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/weapon_cases/crate_sticker_pack_kat2014_02_png.png",
},
],
tournament_event: "Katowice 2014",
tournament_team: "Titan",
type: "Team",
market_hash_name: "Sticker | Titan | Katowice 2014",
effect: "Other",
image:
"https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/stickers/emskatowice2014/titan_png.png",
},
// ...
keychains.json:
{
id: "keychain-1",
name: "Charm | Lil' Ava",
description:
"This charm can be attached to any weapon you own. Each attached charm can be detached by using a Charm Detachment. Detached charms will be returned to your inventory.",
rarity: {
id: "rarity_rare",
name: "High Grade",
color: "#4b69ff",
},
collections: [
{
id: "collection-set-kc-missinglink",
name: "Missing Link Charm Collection",
image:
"https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/set_icons/set_kc_missinglink_png.png",
},
],
market_hash_name: "Charm | Lil' Ava",
image:
"https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/keychains/missinglink/kc_missinglink_ava_png.png",
},
// ...
crates.json:
{
"id": "crate-4904",
"name": "Kilowatt Case",
"description": null,
"type": "Case",
"first_sale_date": "2024-01-31",
"contains": [
{
"id": "skin-135748",
"name": "Dual Berettas | Hideout",
"rarity": {
"id": "rarity_rare_weapon",
"name": "Mil-Spec Grade",
"color": "#4b69ff"
},
"paint_index": "1169",
"image": "https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/default_generated/weapon_elite_dual_berettas_lethal_grin_light_png.png"
}
// ...
],
"contains_rare": [
{
"id": "skin-vanilla-weapon_knife_kukri",
"name": "★ Kukri Knife",
"rarity": {
"id": "rarity_ancient_weapon",
"name": "Covert",
"color": "#eb4b4b"
},
"paint_index": null,
"phase": null,
"image": "https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/weapons/base_weapons/weapon_knife_kukri_png.png"
}
// ...
],
"market_hash_name": "Kilowatt Case",
"rental": true,
"image": "https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/weapon_cases/crate_community_33_png.png",
"model_player": "models/props/crates/csgo_drop_crate_community_33.vmdl",
"loot_list": {
"name": "★ Kukri Knife ★",
"footer": "or an Exceedingly Rare Kukri Knife!",
"image": "https://raw.githubusercontent.com/ByMykel/counter-strike-image-tracker/main/static/panorama/images/econ/weapon_cases/crate_community_33_rare_item_png.png"
}
}
// ...
]
highlights error:
failed to configure com.example.counterdatabase.ui.highlights.HighlightDetailsActivityTest.highlight details activity should handle various scenarios: Package targetSdkVersion=36 > maxSdkVersion=34
java.lang.IllegalArgumentException: failed to configure com.example.counterdatabase.ui.highlights.HighlightDetailsActivityTest.highlight details activity should handle various scenarios: Package targetSdkVersion=36 > maxSdkVersion=34
	at org.robolectric.RobolectricTestRunner.getChildren(RobolectricTestRunner.java:216)
	at org.junit.runners.ParentRunner.getFilteredChildren(ParentRunner.java:534)
	at org.junit.runners.ParentRunner.getDescription(ParentRunner.java:400)
	at androidx.test.ext.junit.runners.AndroidJUnit4.getDescription(AndroidJUnit4.java:157)
	at org.junit.runners.model.RunnerBuilder.configureRunner(RunnerBuilder.java:81)
	at org.junit.runners.model.RunnerBuilder.safeRunnerForClass(RunnerBuilder.java:72)
	at org.junit.internal.builders.AllDefaultPossibilitiesBuilder.runnerForClass(AllDefaultPossibilitiesBuilder.java:37)
	at org.junit.runners.model.RunnerBuilder.safeRunnerForClass(RunnerBuilder.java:70)
	at org.junit.internal.requests.ClassRequest.createRunner(ClassRequest.java:28)
	at org.junit.internal.requests.MemoizingRequest.getRunner(MemoizingRequest.java:19)
	at org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecutor.runTestClass(JUnitTestClassExecutor.java:77)
	at org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecutor.execute(JUnitTestClassExecutor.java:58)
	at org.gradle.api.internal.tasks.testing.junit.JUnitTestClassExecutor.execute(JUnitTestClassExecutor.java:40)
	at org.gradle.api.internal.tasks.testing.junit.AbstractJUnitTestClassProcessor.processTestClass(AbstractJUnitTestClassProcessor.java:54)
	at org.gradle.api.internal.tasks.testing.SuiteTestClassProcessor.processTestClass(SuiteTestClassProcessor.java:53)
	at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(Unknown Source)
	at java.base/java.lang.reflect.Method.invoke(Unknown Source)
	at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:36)
	at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:24)
	at org.gradle.internal.dispatch.ContextClassLoaderDispatch.dispatch(ContextClassLoaderDispatch.java:33)
	at org.gradle.internal.dispatch.ProxyDispatchAdapter$DispatchingInvocationHandler.invoke(ProxyDispatchAdapter.java:92)
	at jdk.proxy1/jdk.proxy1.$Proxy4.processTestClass(Unknown Source)
	at org.gradle.api.internal.tasks.testing.worker.TestWorker$2.run(TestWorker.java:183)
	at org.gradle.api.internal.tasks.testing.worker.TestWorker.executeAndMaintainThreadName(TestWorker.java:132)
	at org.gradle.api.internal.tasks.testing.worker.TestWorker.execute(TestWorker.java:103)
	at org.gradle.api.internal.tasks.testing.worker.TestWorker.execute(TestWorker.java:63)
	at org.gradle.process.internal.worker.child.ActionExecutionWorker.execute(ActionExecutionWorker.java:56)
	at org.gradle.process.internal.worker.child.SystemApplicationClassLoaderWorker.call(SystemApplicationClassLoaderWorker.java:121)
	at org.gradle.process.internal.worker.child.SystemApplicationClassLoaderWorker.call(SystemApplicationClassLoaderWorker.java:71)
	at worker.org.gradle.process.internal.worker.GradleWorkerMain.run(GradleWorkerMain.java:69)
	at worker.org.gradle.process.internal.worker.GradleWorkerMain.main(GradleWorkerMain.java:74)
Caused by: java.lang.IllegalArgumentException: Package targetSdkVersion=36 > maxSdkVersion=34
	at org.robolectric.plugins.DefaultSdkPicker.configuredSdks(DefaultSdkPicker.java:118)
	at org.robolectric.plugins.DefaultSdkPicker.selectSdks(DefaultSdkPicker.java:69)
	at org.robolectric.RobolectricTestRunner.getChildren(RobolectricTestRunner.java:193)
	... 30 more


HighlightDetailsActivityTest > initializationError FAILED
    java.lang.IllegalArgumentException at RobolectricTestRunner.java:216
        Caused by: java.lang.IllegalArgumentException at DefaultSdkPicker.java:118
1 test completed, 1 failed
> Task :app:testDebugUnitTest FAILED
FAILURE: Build failed with an exception.
* What went wrong:
Execution failed for task ':app:testDebugUnitTest'.
> There were failing tests. See the report at: file:///home/henrique/AndroidStudioProjects/CounterDatabase/app/build/reports/tests/testDebugUnitTest/index.html
* Try:
> Run with --scan to get full insights.
BUILD FAILED in 12s
34 actionable tasks: 1 executed, 33 up-to-date
10:10:55: Execution finished ':app:testDebugUnitTest --tests "com.example.counterdatabase.ui.highlights.HighlightDetailsActivityTest"'