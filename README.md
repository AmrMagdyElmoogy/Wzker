# Wzker
## Overview

Wzker is a comprehensive Islamic application that caters to your daily spiritual needs. It serves as a thoughtful reminder for daily Azkar, offers access to random Hadiths from a curated selection of books, and presents a daily Quranic verse accompanied by insightful Tafseer.

## Screenshots 
1. **Light Theme**


   ![Light Theme App](https://github.com/AmrMagdyElmoogy/Wzker/blob/main/app/src/main/java/com/example/wzker/screenshots/light.png)

2. **Dark Theme**


   ![Light Theme App](https://github.com/AmrMagdyElmoogy/Wzker/blob/main/app/src/main/java/com/example/wzker/screenshots/dark.png)
## Architecture

The project adopts the MVVM architecture, ensuring a clean separation of concerns. Each screen is meticulously organized into distinct folders containing Model, ViewModel, and UI components.

## Project Structure

1. **Constants:** This section houses essential constant values, including Quranic surahs with their verses and API endpoints.

2. **Screens:**
   - Each screen is thoughtfully structured into three components: Model, ViewModel, and UI.
  
3. **Notification:**
   - Notify users with distinctive sounds tailored to specific Azkar every 30 minutes.

## Libraries Used

- **Jetpack Compose:** Employed for building the UI in a declarative manner.
- **Retrofit:** Facilitates the handling of network requests.
- **Moshi:** Employed for streamlined JSON parsing.
- **Room:** An invaluable persistence library for local data storage.
- **KSP (Kotlin Symbol Processing):** Enhances compile-time processing capabilities.
- **Lottie:** Integrated for seamless animation displays.
- **Navigation:** Leveraged from the Jetpack library for smooth navigation between different screens.

## APIs Used

- **Quran and Tafseer API:** [Quran Tafseer API Documentation](http://api.quran-tafseer.com/en/docs/)
- **Hadith API:** Explore Hadiths from the [Hadith API GitHub Repository](https://github.com/gadingnst/hadith-api).

Feel free to delve into the project, contribute your insights, and play a part in its continuous enhancement!

