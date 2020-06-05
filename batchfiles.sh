for file in `find ./ -maxdepth 1 -type d|grep -E 'service'`
do
    cd ${file#./};
		sed -i  "/api/d"  pom.xml
    cd ..
done
