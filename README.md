# JetNews 🗞️

JetNews is a modern open-source Android app built using **Jetpack Compose**.  
It demonstrates clean architecture, safe network handling, and the latest Android development practices.

## 📱 Screenshots

<div align="center">
  <img src="screenshots/home_screen.jpeg" width="300" />
  <img src="screenshots/details_screen.jpeg" width="300" />
</div>

## ✨ Features

- Modern UI with **Jetpack Compose**  
- Clean architecture with clear separation of concerns  
- Safe network calls using Retrofit and `safeApiCall`  
- Custom error handling system with support for:  
  - No internet  
  - Server errors  
  - Timeout  
  - Parsing issues  
- **Pagination** support with Paging 3  
- Shared Element Transitions using Compose  
- Stores user preferences using **DataStore**  
- Dependency Injection with **Hilt**  
- Background work using **Kotlin Coroutines** and **Flow**  
- Logging with **Timber**  
- Unit tests using **JUnit**, **MockK**, and **coroutines-test**  

## 🧱 Tech Stack

- **Kotlin**  
- **Jetpack Compose**  
- **Retrofit**  
- **Coroutines** + **Flow**  
- **Paging 3**  
- **ViewModel** (AndroidX)  
- **Hilt** for Dependency Injection  
- **DataStore** for local preferences  
- **JUnit** for unit testing  
- **MockK** for mocking  
- **Timber** for logging  

## 🧰 Error Handling

The app uses a custom error handling system that:

- Wraps API calls with `safeApiCall`  
- Converts exceptions into readable and structured `ErrorEntity`  
- Supports different types of errors (network, timeout, etc.)  
- Uses a base response model for consistent handling  
- Is fully tested in the `network` module  

## 📦 Download APK

👉 [Download Latest APK](https://github.com/osamasayed585/JetNews/releases/latest)  

*(Check the Releases section for signed debug APKs)*  

## ✅ Getting Started

1. Clone this repo:
   ```bash
   git clone https://github.com/osamasayed585/JetNews.git

