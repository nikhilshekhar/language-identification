#!/usr/bin/perl -wl

use Getopt::Long;

my $gold;

GetOptions( "gold=s" => \$gold );

if( not defined $gold ) {
  print STDERR "Usage: evaluate.pl --gold=gold_file < test_file";
  exit;
}


my %labels = ();
open GOLD, "<", $gold or die "Error reading gold file $gold for reading: $!";

  while(<GOLD>) {
    chomp;
    my ($id, $text, $label) = split /\|/;

    $labels{$id} = $label;
  }

close GOLD;


my $total = 0;
my $correct = 0;
my %correctByLg = ();
my %totalByLg = ();
while(<>) {
  chomp;
  my ($id, $label) = split /\|/;

  my $trueLg = $labels{$id};

  if( $label eq $labels{$id} ) {
    $correct ++;
    $correctByLg{ $trueLg } ++;
  }
  $total++;
  $totalByLg{ $trueLg } ++;

}

foreach my $lg ( keys %totalByLg ) {
  my $pct =
    defined $correctByLg{$lg} ?
    100*$correctByLg{$lg}/$totalByLg{$lg} : 0;

  print "$lg: " . sprintf( "%.3f%%", $pct);
}

print "overall: " . sprintf( "%.3f%%", 100*$correct/$total);


