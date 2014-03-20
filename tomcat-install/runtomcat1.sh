#! /bin/sh
# PLEASE CHANGE THE NODE ID . Each tomcat instance on this machine must have its own node id.
# The tomcat alias is here to set a friendly name to your tomcat instances in this machine
# The hostname alias is here to set a friendly name to this machine
# You can also specify the init and max memory you want on this JVM
# Finally, you can set a specific PermGen size (for large Hibernate applications)
# Note : This tomcat session cookie will be suffixed by 'hostname_alias'_'tomcat_alias'_'node_id'
export NODE_ID=1
export TOMCAT_ALIAS=tomcat
export HOSTNAME_ALIAS=$(uname -n)
export JVM_INIT_MEMORY=512m
export JVM_MAX_MEMORY=1024m
export JVM_MAX_PERM_SIZE=256m
#cd my_install_dir
sh tomcat_shared/bin/runtomcat.sh $1
