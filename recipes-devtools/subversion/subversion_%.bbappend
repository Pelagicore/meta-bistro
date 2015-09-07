# Native compilation fails perhaps because of http://svn.haxx.se/dev/archive-2015-05/0087.shtml
# Copied this patch from https://review.tizen.org/gerrit/#/c/31620/
EXTRA_OECONF_class-native += "--with-serf=no"
