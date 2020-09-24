好好学习，天天向上。
git init / clone / add ./ commit -m "" / status / diff / stash /reset / merge
git log --graph --pretty==oneline  reflog[用来记录你的每一次提交命令]

回退
没有add  没有commit【工作区】                   git checkout -- file
有add 没有commit【暂存区】                      git reset HEAD file
有add 有commit  【仓库】                        git reset --hard HEAD^
git reset命令即可以回退版本，也可以把暂存区的修改回退到工作区。

要关联一个远程库，使用命令git remote add origin git@server-name:path/repo-name.git；
关联后，使用命令git push -u origin master第一次推送master分支的所有内容；git push --set-upstream origin dev
此后，每次本地提交后，只要有必要，就可以使用命令git push origin master推送最新修改；

cat file 查看文件内容



git 分支管理
git branch / git checkout -b dev == git branch dev + git checkout dev / git branch -a / git branch -d branchName
修改分支名：
git branch -m oldBranchName newBranchName [本地分支重命名]
1、git branch -m oldBranchName newBranchName 2、git push --delete origin oldBranchName 3、git push -u origin newBranchName  [远程分支重命名 先修改本地然后删除远程老分支最后上传新的重命名的分支]


合并 merge / rebase
git merge命令用于合并指定分支到当前分支。
Git用<<<<<<<，=======，>>>>>>>标记出不同分支的内容

bug issus-101