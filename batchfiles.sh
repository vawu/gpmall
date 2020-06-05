for file in `find ./ -maxdepth 1 -type d|grep -E 'service'`
do
    cd ${file#./};
		mv *-api ../gpmall-commons/
    cd ..
done
