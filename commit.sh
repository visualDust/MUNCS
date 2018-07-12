clear
echo Adding files .
echo Please wait...
echo 

echo > README.md
cat RM >> README.md

echo \# 最近一次COMMIT >> README.md
echo >> README.md
echo COMMIT的时间 >> README.md
date >> README.md
echo --- >> README.md

echo \> 是谁COMMIT >> README.md
echo >> README.md
echo Commit using : >> README.md
echo \`\`\` >> README.md
git config --get user.name >> README.md
git config --get user.email >> README.md
echo \`\`\` >> README.md
echo --- >> README.md

echo \> 当前分支 >> README.md
echo >> README.md
git config --get remote.origin.url >> README.md
git branch >> README.md
echo >> README.md
echo --- >> README.md

echo \> COMMIT的细节 >> README.md
echo >> README.md
git status >> README.md
echo --- >> README.md

echo \> 当前文件列表 >> README.md
echo >> README.md
echo \`\`\` >> README.md
dir -R  >> README.md
echo \`\`\` >> README.md
echo >> README.md
echo Auto commit by VisualDust >> README.md
git add --all 
echo Add files : Finished .
git commit
exit
