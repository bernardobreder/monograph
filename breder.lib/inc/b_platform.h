
#if defined(linux) || defined(__linux) || defined(__linux__)
#undef __UNIX__
#define __UNIX__	1
#undef __LINUX__
#define __LINUX__	1
#endif

#if defined(__APPLE__)
#undef __UNIX__
#define __UNIX__	1
#undef __MACOS__
#define __MACOS__	1
#endif

#if defined(WIN32) || defined(_WIN32)
#undef __WINDOWS__
#define __WINDOWS__	1
#endif
