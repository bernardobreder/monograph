#ifndef B_LOG_H_
#define B_LOG_H_

#include "breder.h"

void b_log_public_print(const char *fmt, ...) ;

void b_log_public_print_error(const char *fmt, ...);

#ifdef _DEBUG_

void b_log_print_info(const char *fmt, ...);

void b_log_print(const char *fmt, ...) ;

void b_log_print_error(const char *fmt, ...);

#else

#define b_log_print_info(...) {}
#define b_log_print_error(...) {}
#define b_log_print(...) {}

#endif

#endif
