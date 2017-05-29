/*
 * This file is the 'memsnoop' application.  memsnoop is free software: you can
 * redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, version 2.

 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for
 * more details.
 *
 * Copyright (C) 2017 Pelagicore
 *
 * This application is used to read and write raw memory via the /dev/mem
 * interface. Please note that reading/writing the wrong locations in memory
 * is dangerous, and can damage your system. Only use this application on
 * memory addresses you know are safe, and preferrably in a virtual machine.
 *
 * You can find interesting memory locations in /proc/iomem.
 *
 * Usage: memsnoop read 0x<address> <num bytes to read>
 * or
 * memsnoop write 0x<address> 0x<offset in bytes> 0x<byte value to write>
 */

#include <unistd.h>
#include <stdio.h>
#include <fcntl.h>
#include <assert.h>
#include <stdlib.h>
#include <string.h>
#include <sys/mman.h>

#define ERROR(x) {                                                            \
                    fprintf(stderr, "%s\n", ((x)));                           \
                    exit(1);                                                  \
                 }

void read_bytes(char *mem, long addr, unsigned int size) {
    while(size) {
        int i = 16;
        printf("0x%lx: ", addr);
        for (; i > 0 && size > 0; i--, size--, addr++) {
            printf("%.2x ", *mem++);
        }
        printf("\n");
    }
}

void write_bytes(char *mem, unsigned int offset, char value) {
    *(mem+offset) = value;
}

void usage(char *name) {
    fprintf(stderr, "USAGE: %s read 0x<address> <num bytes to read>\n"
           "%s write 0x<address> 0x<offset in bytes> 0x<byte value to write>\n",
           name, name);
}

int main(int argc, char *argv[]) {
    char *mem, *command;
    int fd;
    long addr;
    unsigned int size;

    if (argc < 4) {
        usage(argv[0]);
        exit(1);
    }

    if (sscanf(argv[1], "%ms", &command) <= 0) {
        usage(argv[0]);
        ERROR("\nFailed to read command (read/write)");
    }

    if (sscanf(argv[2],"%10lx", &addr) <= 0) {
        usage(argv[0]);
        ERROR("\nFailed to read address (must be hexadecimal, prefixed with 0x)");
    }

    fd = open ("/dev/mem", O_RDWR);

    if (fd <= 0) {
        ERROR("Failed to open /dev/mem");
    }
    mem = mmap(NULL, getpagesize(), PROT_READ|PROT_WRITE, MAP_SHARED, fd,
               (off_t) addr);
    assert(mem != MAP_FAILED);

    if (!strncmp(command, "read", 4)) {
        if (sscanf(argv[3],"%10u", &size) <= 0) {
            usage(argv[0]);
            ERROR("\nFailed to read count (must be decimal integer value)");
        }

        read_bytes(mem, addr, size);
    } else if (!strncmp(command, "write", 5)) {
        unsigned char value;
        unsigned long offset;

        if (argc != 5) {
            usage(argv[0]);
            ERROR("\nNot enough arguments");
        }

        if (sscanf(argv[3],"%10lx", &offset) <= 0) {
            usage(argv[0]);
            ERROR("\nFailed to read offset (must be hexadecimal, prefixed with 0x)");
        }

        if (sscanf(argv[4]+2,"%02hhx", &value) <= 0) {
            usage(argv[0]);
            ERROR("\nFailed to read value (must be hexadecimal, prefixed with 0x)");
        }

        write_bytes(mem, offset, value);
    } else {
        usage(argv[0]);
        exit(1);
    }

    munmap(mem, getpagesize());

    close(fd);
    return 0;
}
