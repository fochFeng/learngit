好好学习，天天向上。
git init / add ./ commit -m "" / status / diff / stash
git log --pretty==oneline  reflog[用来记录你的每一次提交命令]

回退
没有add  没有commit【工作区】                   git checkout -- file
有add 没有commit【暂存区】                      git reset HEAD file
有add 有commit  【仓库】                        git reset --hard HEAD^
git reset命令即可以回退版本，也可以把暂存区的修改回退到工作区。

要关联一个远程库，使用命令git remote add origin git@server-name:path/repo-name.git；
关联后，使用命令git push -u origin master第一次推送master分支的所有内容；
此后，每次本地提交后，只要有必要，就可以使用命令git push origin master推送最新修改；

cat file 查看文件内容