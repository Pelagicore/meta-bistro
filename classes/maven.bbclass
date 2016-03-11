#
#   Copyright (C) 2015 Pelagicore AB
#

inherit maven-properties

#DEPENDS_prepend = "maven-native "

maven_do_configure() {
	cd ${B}
}

maven_do_compile()  {
	cd ${B}
	mvn ${MAVEN_OPTIONS} package
}

maven_do_install() {
	cd ${B}
	mvn ${MAVEN_OPTIONS} install
}

EXPORT_FUNCTIONS do_configure do_compile do_install

BBCLASSEXTEND = "native nativesdk"

