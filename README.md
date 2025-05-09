# JetNews 🗞️

JetNews is an open-source Android app built using **Jetpack Compose**, designed to demonstrate clean architecture, modern Android development practices, and robust **Error Handling** with **Retrofit**.

## 📸 Screenshots
<div align="center">
  <img src="screenshots/home_screen.jpeg" width="300" />
  <img src="screenshots/details_screen.jpeg" width="300" />
</div>



## 🚀 Features

- Modern UI using **Jetpack Compose**
- Clean and scalable **network layer**
- Custom **Error Handling** with clear structure
- Safe API calls using `safeApiCall`
- Well-structured code following **Clean Architecture**
- Fully tested **network module** using Unit Tests

## 📦 Technologies Used

- Kotlin
- Jetpack Compose
- Retrofit
- Coroutines
- Flow
- ViewModel (from AndroidX)
- Hilt for Dependency Injection
- JUnit for unit testing

## 🔧 Error Handling System

This project includes a **custom error handling system** that:

- Covers different error types: Network, Server, Timeout, Parsing, etc.
- Uses a unified `BaseResponse` structure
- Converts exceptions to readable `ErrorEntity`
- Ensures safety with a `safeApiCall` wrapper
- Is fully covered with unit tests

Check it out inside the `network` module!

## 🛠️ Getting Started

1. Clone this repository:
   ```bash
   git clone https://github.com/osamasayed585/JetNews.git

