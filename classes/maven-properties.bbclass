#
#   Copyright (C) 2015 Pelagicore AB
#

#MAVEN_OPTIONS = "-Dmaven.repo.local=${SYSROOT_DESTDIR}/MavenRepository"
#MAVEN_OPTIONS = "-Dmaven.repo.local=/tmp/mavenRepo333"
#MAVEN_OPTIONS = "-Dmaven.repo.local=${WORKDIR}/../../MavenRepository"

# the "base_prefix" variable points to the root of the sysroot, which seems to be a reasonable place to 
# put our Maven repository, which needs to be shared among our components using Maven
# TODO : check whether this is safe to assume
#MAVEN_OPTIONS_native = "-Dmaven.repo.local=${base_prefix}/MavenRepository"
#MAVEN_OPTIONS_nativesdk = "-Dmaven.repo.local=${PKG_CONFIG_SYSROOT_DIR}/MavenRepository"

do_configure_prepend() {
	# check whether Maven is available, since we rely on the system's Maven for now.
	LOCATION="`which mvn`" || true
	if [ -z "$LOCATION" ] ; then
		echo "You need to install the Maven package on your system.\nOn Ubuntu/Debian:\n  $ sudo apt-get install maven"
		exit 1
	fi
}

EXTRA_OECMAKE += " -DMAVEN_REPOSITORIES_LOCATION=${STAGING_DIR_HOST}/${includedir}/MavenRepositories"
