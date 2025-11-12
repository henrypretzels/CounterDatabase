# Test Documentation for `main` Branch

This document provides a summary of the tests currently implemented in the `main` branch. It serves as a reference to understand the existing test coverage.

---

### 1. SkinsViewModelTest

- **File Location:** `app/src/test/java/com/example/counterdatabase/ExampleUnitTest.kt`
- **Type:** Local Unit Test (Runs on the JVM).
- **Goal:** To verify the search and filter business logic of the `SkinsViewModel` in complete isolation from the Android framework.

**What it Tests:**
- `searchSkins should filter the list correctly`: Checks if searching for a term like "Dragon" correctly returns only skins whose names contain that term.
- `searchSkins should be case-insensitive`: Ensures the search logic ignores case (e.g., "dragon" matches "Dragon Lore").
- `searchSkins with empty query should return the full list`: Confirms that an empty search string (`""`) returns the original, unfiltered list.
- `searchSkins with null query should return the full list`: Confirms that a `null` search query also returns the unfiltered list.
- `searchSkins with no matching results should return an empty list`: Verifies that searching for a term that doesn't exist returns an empty list.

---

### 2. StickersViewModelTest

- **File Location:** `app/src/test/java/com/example/counterdatabase/ui/stickers/StickersViewModelTest.kt`
- **Type:** Local Unit Test (Runs on the JVM).
- **Goal:** To verify the search logic of the `StickersViewModel`, which handles filtering by multiple fields.

**What it Tests:**
- Correctly filters the sticker list by `name`, `tournament_team`, and `tournament_event`.
- Ensures that the search is case-insensitive across all fields.
- Confirms that `null` or empty search queries return the full, unfiltered list.
- Verifies that a non-matching query returns an empty list.

**Status:** One test is currently failing. This is a positive outcome, as it means the test suite is successfully identifying a bug in the ViewModel's filtering logic that needs to be fixed.

---

### 3. HighlightDetailsActivityTest

- **File Location:** `app/src/androidTest/java/com/example/counterdatabase/ui/highlights/HighlightDetailsActivityTest.kt`
- **Type:** Instrumented Test (Runs on an Android device or emulator).
- **Goal:** To verify the UI behavior, data display, and lifecycle management of the `HighlightDetailsActivity`.

**Instructions to Run:**
1.  In Android Studio, right-click on the `HighlightDetailsActivityTest.kt` file in the Project view.
2.  Select **Run 'HighlightDetailsActivityTest'**.
3.  Choose an available Android emulator or a connected physical device.

**What it Tests:**
- `displaysAllDetails_whenHighlightIsComplete`: Checks that all UI elements (name, description, tournament, video player, etc.) are correctly displayed and visible when a complete `Highlight` object is passed to the activity.
- `hidesEmptyViews_whenDataIsMissing`: Verifies that UI elements corresponding to `null` or empty data fields in the `Highlight` object are correctly hidden (`View.GONE`) to avoid showing empty space.
- `playerIsReleased_onStop`: A critical lifecycle test that ensures the `ExoPlayer` instance is properly initialized when the activity starts and, more importantly, released (`null`) when the activity stops. This prevents video playback from continuing in the background and avoids memory leaks.
