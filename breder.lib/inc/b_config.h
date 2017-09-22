#ifndef B_CONFIG_H_
#define B_CONFIG_H_

#include "b_platform.h"

#ifdef __LINUX__
#include "b_config_linux.h"
#endif

#ifdef __MACOS__
#include "b_config_macos.h"
#endif

#ifdef __WINDOWS__
#include "b_config_windows.h"
#endif

#endif
