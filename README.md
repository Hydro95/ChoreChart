# ChoreChart

This is a chore chart to keep track of chores around the house, ideal for families or other
people living together.

This project was created in 2017 for the course SEG2105 at the University of Ottawa.

# Contributors

LEAD: Joshua Vinge, 8655939

# Contribution Instructions

In order to make this clear + avoid most merge conflicts, you should first make your own fork of this repo, commit your changes to your own version, and then make a pull request on the github page so changes can be reviewed + conflicts can be resolved easier. Here is what you need to do:

## Fork the repo

On this page, you'll see a fork button in the top right. Click that and the repo will be forked to your own github account. Clone your new version into a directory on your computer in a terminal window using

{% highlight %}
  git clone [your new forked repo url]
{% endhighlight %}

## Set up remotes

Now that you have your own forked repo on your computer you need to set up remotes so you can fetch updates from the main repo. Enter the working directory of the repo on your computer and type into a terminal

{% highlight %}
  git remote add upstream https://github.com/Hydro95/ChoreChart.git
  git fetch upstream
{% endhighlight %}

Performing {% highlight %} git fetch upstream {% endhighlight %} will fetch changes from the main repository and merge them with your fork. Make sure you always run this before making any changes. To double check, make sure the amount of commits on the main repo matches the amount of commits on your forked repo (visible on the github site)

## Make changes

You can now make any changes and commit/push like normal. Your changes will go to your own fork instead of the main repo so you won't have to worry about merge conflicts until you make a pull request later.

## Making a pull request

When you're finished making changes, commit and push your changes. Visit the github page for your fork and you'll see a new button called "New Pull Request" To the left of the branch selector. Click this and compare the changes to the master branch. If there are no conflicts the files will be marked as able to merge without conflict and you can submit the pull request. The pull request will appear on the main repo page to be reviewed and closed, which merges the changes into the main repo.

# PS

If you haven't already done so, make sure to sign up for Hacktoberfest at http://hacktoberfest.digitalocean.com/ . By making 4 pull requests or more during October you can get a bunch of free stickers and a T shirt.
