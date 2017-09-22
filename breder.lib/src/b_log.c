#include "breder.h"

#ifdef _DEBUG_

void b_log_print_info(const char *fmt, ...) {
	va_list ap;
	va_start(ap,fmt);
	b_char_t* msg = b_char_new3("[info] : ", fmt, "\n");
	vprintf(msg, ap);
	b_char_free(msg);
	va_end(ap);
	b_so_out_flush();
}

void b_log_print_error(const char *fmt, ...) {
	va_list ap;
	va_start(ap,fmt);
	b_char_t* msg = b_char_new3("[error] : ", fmt, "\n");
	vprintf(msg, ap);
	b_char_free(msg);
	va_end(ap);
	b_so_out_flush();
}

void b_log_print(const char *fmt, ...) {
	va_list ap;
	va_start(ap,fmt);
	vprintf(fmt, ap);
	va_end(ap);
	b_so_out_flush();
}

#else

#endif

void b_log_public_print_error (const char *fmt, ...) {
	va_list ap;
	va_start(ap,fmt);
	b_char_t* msg = b_char_new3 ("[error] : ", fmt, "\n");
	vprintf (msg, ap);
	b_char_free (msg);
	va_end(ap);
}

void b_log_public_print (const char *fmt, ...) {
	va_list ap;
	va_start(ap,fmt);
	vprintf (fmt, ap);
	va_end(ap);
}
