git --version

git config --global user.name "myUserName"
git config --global user.email myName@email.com

cd
mkdir my-first-repo
cd my-first-repo

git init

echo "Welcome to My First Repo" > readme.txt
git status
git add readme.txt
git rm --cached readme.txt
git add --all
git commit -m "added readme.txt"

git reset --soft HEAD~
git help pull
git log
git diff readme.txt
git checkout readme.txt
echo "*.jpg" > .gitignore

git branch my-new-feature		# -d for delete
git checkout my-new-feature
git branch
git checkout master
git merge my-new-feature


git remote add origin https://github.com/seankross/my-first-repo.git
git remote
git pull master
git push -u origin master
